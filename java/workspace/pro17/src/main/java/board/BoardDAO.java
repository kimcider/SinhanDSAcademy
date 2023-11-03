package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;

	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "testuser", "test1234");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 검색어/페이지번호 등등을 입력 받아서 해당 게시판 목록을 리턴
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> list = new ArrayList<>();
		try {
			String query = "SELECT articleno, title, content, id, writedate, nested"
							+ " FROM t_board"
							+ " ORDER BY gno DESC, ono ASC";
							//이거 왜 gno desc, ono asc인지 확실히 이해해야한다!!!
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
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
	
	public void insertNewArticle(ArticleVO article) {
		try {
			String query = "INSERT INTO t_board(articleno, title, content, imagefilename, id, gno, ono, nested)"
							+ "VALUES(SEQ_T_BOARD.NEXTVAL, ?, ?, ?, ?, SEQ_T_BOARD.CURRVAL, 0, 0)";
			//일단 gno, ono, nested를 0으로 넣고, 업데이트를 칠 것
			//articleno를 널때는 새로운 글번호를 가져와야하니까 nextval
			// gno입력해줄때는 현재 articleno를 입력해줘야하니까 currval
			
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getImagefilename());
			pstmt.setString(4, article.getId());
			
			//등록/삭제/수정은 등록이 된 갯수를 리턴한다. 때문에 여기서 받아온 값을 int로 리턴받아서 사용하는 경우도 있다.
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}