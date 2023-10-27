package DataBase._01_connectionExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
					
			System.out.println("연결성공");
			
			//SQL을 실행할 객체 생성
			stmt = conn.createStatement();
			//SQL을 실행
//			rs = stmt.executeQuery("SELECT * FROM student ORDER BY name DESC"); //가독성을 위해 SELECT 등은 대문자로 작성해라
			
			
			//사용자로부터 id, pwd를 입력받아 그런 회원이 있는지를 조회하고자 할 때 다음과 같이 sql문을 작성해야한다. 
			String id = "";
			String pwd = "";
			String sql = "SELECT * FROM users WHERE userid = '" + id + "' AND userpassword = '" + pwd +"'";
			sql = "select * from boards";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getString("bno"));
			// 그런데 가령 내가 아이디를 안다고 가정할 경우, 
			id = "admin";//이고 비번은 모른다.
			//이 경우
			id = "admin' --";//이런식으로 작성하면 id뒤가 주석처리되어 아이디만 있어도 로그인이 가능하다....
			
			//그래서 요즘은 아래와 같은 방식(preparedStatement)으로 사용한다.
			sql = "SELECT * FROM users WHERE userid = ? AND userpassword = ?";
			//이렇게 한 다음 ?자리에 문자를 채워넣는다. 
			//이렇게 하면, sql문자열을 DB에 미리 준비를 시켜둔 후, 물음표 자리에 값을 채우는 방식이다. 
			//그리고 DB에 미리 sql문자열을 준비시켜둔 후, 값만 넘겨주면서 처리를 하니 속도도 훨씬 빠르다. 
			
			//따라서 앵간해서는 prepared로 사용해라!!
			
			
			
			//실행결과를 처리
			while(rs.next()) {
				System.out.println(rs.getString("name") + "\t" + rs.getString("id"));
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(Exception e){}
			if(stmt != null) try {stmt.close();} catch(Exception e){}
			if(conn != null) {
				try {
					conn.close();
					System.out.println("연결끊기");
				}catch(SQLException e) {}
			}
		}
	}
}
