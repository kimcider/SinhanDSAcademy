package sec01.ex01_connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//DAO는 본래는 싱글톤으로 해야한다.
public class MemberDAO_01_Statement {
	private Statement stmt;
	private Connection con;
	
	private void connDB() {
		try {
			//클래스 로드 (ojdbc.jar파일 내에 있는 클래스를 로드)
			Class.forName("oracle.jdbc.OracleDriver");
			//커넥션 객체 생성
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
			
			// statement 객체 생성
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<>();
		try {
			connDB(); // DB에 접속
			
			// DB에서 데이터를 요청하고, 받은 데이터들을 VO에 넣어 반환
			String query = "SELECT * FROM t_member";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setJoinDate(rs.getDate("joinDate"));
				list.add(vo);
			}
			
			// close할때는 나중에 만들어진 것을 먼저 close한다.
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}