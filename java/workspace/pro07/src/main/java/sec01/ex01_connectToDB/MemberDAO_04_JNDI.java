package sec01.ex01_connectToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DAO는 본래는 싱글톤으로 해야한다.
public class MemberDAO_04_JNDI {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	MemberDAO_04_JNDI() {
		try {
			/*
			 	context.xml에 아래의 정보들을 모두 적어놓았으니 아래 정보들이 필요 없다.
			 	
				Class.forName("oracle.jdbc.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "testuser", "test1234");
				stmt = con.createStatement();
			*/
			
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env"); // 여기까지는 경로를 바꾸면 안된다.
			// 이렇게 전역변수로 선언된 dataFactory변수에 datasource를 넣어둔다.
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // context.xml의 리소스에 넣었던 그 네임을 넣어야한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			// 사용할 때는 위에 connection pool을 넣어둔 dataFactory객체에서 커넥션을 받아서 사용한다.
			con = dataFactory.getConnection();
			stmt = con.createStatement();
			
			String query = "SELECT * FROM t_member";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setJoinDate(rs.getDate("joinDate"));
				list.add(vo);
			}

			rs.close();
			pstmt.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}