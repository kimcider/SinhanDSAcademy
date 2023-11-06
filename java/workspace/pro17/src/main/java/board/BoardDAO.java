package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env"); // 여기까지는 경로를 바꾸면 안된다.

			// 이렇게 전역변수로 선언된 dataFactory변수에 datasource를 넣어둔다.
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // context.xml의 리소스에 넣었던 그 네임을 넣어야한다.
			con = dataFactory.getConnection();
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			con.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 검색어/페이지번호 등등을 입력 받아서 해당 게시판 목록을 리턴
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> list = new ArrayList<>();
		try {
			String query = "SELECT articleno, title, content, id, writedate, nested" + " FROM t_board"
					+ " ORDER BY gno DESC, ono ASC";
			// 이거 왜 gno desc, ono asc인지 확실히 이해해야한다!!!

			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setArticleno(rs.getInt("articleno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setWritedate(rs.getDate("writedate"));
				vo.setNested(rs.getInt("nested"));
				list.add(vo);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArticleVO selectArticle(int no) {
		ArticleVO vo = new ArticleVO();

		try {
			String query = "SELECT articleno, title, content, id, writedate, imagefilename" + " FROM t_board"
					+ " WHERE articleno=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setArticleno(rs.getInt("articleno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setWritedate(rs.getDate("writedate"));
				vo.setImagefilename(rs.getString("imagefilename"));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public void insertNewArticle(ArticleVO article) {
		try {
			String query = "INSERT INTO t_board(articleno, title, content, imagefilename, id, gno, ono, nested)"
					+ "VALUES(SEQ_T_BOARD.NEXTVAL, ?, ?, ?, ?, SEQ_T_BOARD.CURRVAL, 0, 0)";
			// 일단 gno, ono, nested를 0으로 넣고, 업데이트를 칠 것
			// articleno를 널때는 새로운 글번호를 가져와야하니까 nextval
			// gno입력해줄때는 현재 articleno를 입력해줘야하니까 currval

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getImagefilename());
			pstmt.setString(4, article.getId());

			// 등록/삭제/수정은 등록이 된 갯수를 리턴한다. 때문에 여기서 받아온 값을 int로 리턴받아서 사용하는 경우도 있다.
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateArticle(ArticleVO vo) {
		try {
			//이렇게 해두고 null을 replace해주면안되나?
//			String query = "UPDATE t_board SET title=?, content=?, imagefilename=null ";
			String query = "UPDATE t_board SET title=?, content=? ";

			System.out.println("imgfilename: " + vo.getImagefilename());
			if (vo.getImagefilename() != null || "".equals(vo.getImagefilename())) {
				query += ", imagefilename=? ";
			}
			query += " WHERE articleno=?";
			
			System.out.println("query: " + query);
			//이렇게 number로 쓰면
			int pstmtInt = 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(pstmtInt++, vo.getTitle());
			pstmt.setString(pstmtInt++, vo.getContent());
			if (vo.getImagefilename() != null || "".equals(vo.getImagefilename())) {
				pstmt.setString(pstmtInt++, vo.getImagefilename());
			}
			//이런식으로 if문을 써주지 않아도 된다. 
			//if()pstmt.setInt(3, vo.getArticleno());
			//else()pstmt.setInt(4, vo.getArticleno());
			pstmt.setInt(pstmtInt++, vo.getArticleno());
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 이미지 파일 명 리턴
	public List<String> selectImagefilename(String articleno){
		List<String> list = new ArrayList<>();
		
		try {
			String query = "SELECT imagefilename FROM t_board WHERE gno=" + articleno;
			// 이거 왜 gno desc, ono asc인지 확실히 이해해야한다!!!

			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("imagefilename"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	// delete
	public int deleteArticle(String articleno) {
		int result = 0;
		try {
			//이렇게 해두고 null을 replace해주면안되나?
//			String query = "UPDATE t_board SET title=?, content=?, imagefilename=null ";
			String query = "DELETE FROM t_board WHERE gno=" + articleno;
			pstmt = con.prepareStatement(query);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}