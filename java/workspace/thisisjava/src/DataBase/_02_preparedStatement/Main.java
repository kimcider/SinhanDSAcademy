package DataBase._02_preparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			String sql = "INSERT INTO users "
					+ "(userid, username, userpassword, userage, useremail) "
					+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");
			pstmt.setString(2, "한겨울");
			pstmt.setString(3, "12345");
			pstmt.setInt(4, 25);
			pstmt.setString(5, "winte@mycompany.com");
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수: " + rows);
			
//			PreparedStatement pstmt = conn.prepareStatement(sql);
			int pstmtInt = 1;
			pstmt.setString(pstmtInt++, "summer");
			pstmt.setString(pstmtInt++, "여름");
			pstmt.setString(pstmtInt++, "12345");
			pstmt.setInt(pstmtInt++, 25);
			pstmt.setString(pstmtInt++, "summer@mycompany.com");
			//이런식으로 코딩을 하면 중간에 (userid, username, userpassword, userage, useremail) 이 속성부분에 특정 속성이 추가되서
			//setString(1, ) setString(2, )의 값을 하나씩 미뤄야 할 경우 일일히 수정해줘야 할 필요가 없다. 
			
			rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수: " + rows);
			pstmt.close();
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
