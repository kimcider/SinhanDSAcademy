package sec01.ex01_connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO_03_DataSource {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	
	MemberDAO_03_DataSource(){
		/* 생성자를 통해 DAO를 생성할 때 DB와의 커넥션을 설정한다. */
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 	작업을 수행할 때 connDB()를 통해 매번 커넥션을 새로 만들지 않고, 
	    미리 연결해둔 con과 stmt를 사용해 작업을 수행한다. 
	*/
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<>();
		try {
			String query = "SELECT * FROM t_member";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setJoinDate(rs.getDate("joinDate"));
				list.add(vo);
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}