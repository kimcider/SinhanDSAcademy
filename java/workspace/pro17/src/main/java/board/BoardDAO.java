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
			con = dataFactory.getConnection();
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 페이징 처리 + 검색
	public List<ArticleVO> selectPagingArticles(ArticleVO param) {
		List<ArticleVO> list = new ArrayList<>();

		try {
			con = dataFactory.getConnection();

			String query = "";
			query += "SELECT\r\n"
					+ "		ROWNUM rn, articleno, title ,content, imagefilename, writedate, id, gno, ono, nested\r\n"
					+ "FROM(\r\n"
					+ "    SELECT ROWNUM rn, articleno, title ,content, imagefilename, writedate, id, gno, ono, nested\r\n"
					+ "    FROM(\r\n" + "        SELECT\r\n"
					+ "            articleno, title ,content, imagefilename, writedate, id, gno, ono, nested\r\n"
					+ "        FROM t_board\r\n";

/*	근데 이런식으로 하면 WHERE가 두번들어가서 에러가난다 ㅎㅎ. 그러면 보통 어떻게하냐 */
//			//searchWord추가
//				//제목으로 검색	
//			if(param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
//				query += " WHERE title LIKE '%" + param.getSearchWord() + "%'";
//			}
//				//내용으로 검색
//			if(param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
//				query += " WHERE content LIKE '%" + param.getSearchWord() + "%'";
//			}
//			
//				//아이디로 검색
//			if(param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
//				query += " WHERE id LIKE '%" + param.getSearchWord() + "%' OR content LIKE '%" + param.getSearchWord() + "%'";
//			}
			
			//아래처럼 query의 맨 마지막에 무조건 참인 조건을 하나 넣어주고
			query += " WHERE 1=1 ";
			// searchWord추가
			// 제목으로 검색
			/* 얘들을 and조건으로 바꿔준다. */
//			System.out.println("inner searchWord: " + param.getSearchWord());
//			if (param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
//				query += " AND title LIKE '%" + param.getSearchWord() + "%'";
//			}
//			// 내용으로 검색
//			if (param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
//				query += " AND content LIKE '%" + param.getSearchWord() + "%'";
//			}

			// 아이디로 검색
			if (param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
				query += " AND (title LIKE '%" + param.getSearchWord() + "%' OR content LIKE '%" + param.getSearchWord()
						+ "%')";
			}

			query += "        ORDER BY gno DESC, ono ASC\r\n" + "    )\r\n" + "    WHERE ROWNUM <= ?\r\n" + ")\r\n"
					+ "WHERE rn > ?";
			
			System.out.println("query: " + query);
			System.out.println("이하: " + param.getPage() * param.getRowPage());
			System.out.println("이하: " + (param.getPage() - 1) * param.getRowPage());
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, param.getPage() * param.getRowPage());
			pstmt.setInt(2, (param.getPage() - 1) * param.getRowPage());
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArticleVO selectArticle(int no) {
		ArticleVO vo = new ArticleVO();

		try {
			con = dataFactory.getConnection();
			String query = "SELECT articleno, title, content, id, writedate, imagefilename, gno, ono, nested"
					+ " FROM t_board" + " WHERE articleno=?";
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
				vo.setGno(rs.getInt("gno"));
				vo.setOno(rs.getInt("ono"));
				vo.setNested(rs.getInt("nested"));
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public void insertNewArticle(ArticleVO article) {
		try {
			con = dataFactory.getConnection();
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateArticle(ArticleVO vo) {
		try {
			con = dataFactory.getConnection();
			// 이렇게 해두고 null을 replace해주면안되나?
//			String query = "UPDATE t_board SET title=?, content=?, imagefilename=null ";
			String query = "UPDATE t_board SET title=?, content=? ";

			System.out.println("imgfilename: " + vo.getImagefilename());
			if (vo.getImagefilename() != null || "".equals(vo.getImagefilename())) {
				query += ", imagefilename=? ";
			}
			query += " WHERE articleno=?";

			System.out.println("query: " + query);
			// 이렇게 number로 쓰면
			int pstmtInt = 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(pstmtInt++, vo.getTitle());
			pstmt.setString(pstmtInt++, vo.getContent());
			if (vo.getImagefilename() != null || "".equals(vo.getImagefilename())) {
				pstmt.setString(pstmtInt++, vo.getImagefilename());
			}
			// 이런식으로 if문을 써주지 않아도 된다.
			// if()pstmt.setInt(3, vo.getArticleno());
			// else()pstmt.setInt(4, vo.getArticleno());
			pstmt.setInt(pstmtInt++, vo.getArticleno());

			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이미지 파일 명 리턴
	public List<String> selectImagefilename(String articleno) {
		List<String> list = new ArrayList<>();

		try {
			con = dataFactory.getConnection();
			String query = "SELECT imagefilename FROM t_board WHERE gno=" + articleno;
			// 이거 왜 gno desc, ono asc인지 확실히 이해해야한다!!!

			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("imagefilename"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// delete
	public int deleteArticles(String articleno) {
		int result = 0;
		try {
			con = dataFactory.getConnection();
			// 이렇게 해두고 null을 replace해주면안되나?
//			String query = "UPDATE t_board SET title=?, content=?, imagefilename=null ";
			String query = "DELETE FROM t_board WHERE gno=" + articleno;
			pstmt = con.prepareStatement(query);
			result = pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 답글 등록
	public void insertReplyArticle(ArticleVO article) {
		try {
			con = dataFactory.getConnection();
			String query = "INSERT INTO t_board(articleno, title, content, imagefilename, id, gno, ono, nested)"
					// 이제는 gno, ono, nested도 모두 article에 담겨오기 때문에 이들도 모두 ?로 적는다.
					+ "VALUES(SEQ_T_BOARD.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("query: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getImagefilename());
			pstmt.setString(4, article.getId());
			pstmt.setInt(5, article.getGno());
			pstmt.setInt(6, article.getOno());
			pstmt.setInt(7, article.getNested());

			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateOno(ArticleVO vo) {
		try {
			con = dataFactory.getConnection();
			// articleVO vo는 부모vo가오겠지?
			// 부모의 gno와 ono가 필요하다.
			String query = "UPDATE t_board SET ono=ono+1 WHERE gno=? AND ono>?";

			int pstmtInt = 1;
			pstmt = con.prepareStatement(query);
			System.out.println("update_vo.getGNO: " + vo.getGno());
			System.out.println("update_vo.getONO: " + vo.getOno());
			pstmt.setInt(pstmtInt++, vo.getGno());
			pstmt.setInt(pstmtInt++, vo.getOno());

			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 파람에 검색어가 있냐없냐에 따라서 출력되는 총 결과문들이 달라지기 때문에
	// 이거는 selectPagingArticle이랑 구조가 유사해야한다.
	public int selectCounter(ArticleVO param) {
		ArticleVO vo = new ArticleVO();
		int count = 0;

		try {
			con = dataFactory.getConnection();
			String query = "SELECT COUNT(*) AS cnt FROM t_board WHERE 1=1 ";
			// 검색 조건 설정
			if (param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
				query += " AND title LIKE '%" + param.getSearchWord() + "%'";
			}
			if (param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
				query += " AND content LIKE '%" + param.getSearchWord() + "%'";
			}

			if (param.getSearchWord() != null && !"".equals(param.getSearchWord())) {
				query += " AND id LIKE '%" + param.getSearchWord() + "%' OR content LIKE '%" + param.getSearchWord()
						+ "%'";
			}

			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("cnt");
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
}