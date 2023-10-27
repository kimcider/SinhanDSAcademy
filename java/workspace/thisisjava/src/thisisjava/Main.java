package thisisjava;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
	public static void main(String[]args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
			
			String sql = new StringBuilder()
						.append("UPDATE boards SET")
						.append("btitle = ?, ")
						.append("bcontent = ?, ")
						.append("bfilename = ?, ")
						.append("bfiledate = ?, ")
						.append("WHERE bno = ?")
						.toString();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "눈사람");
			pstmt.setString(2, "눈으로 만든 사람");
			pstmt.setString(3, "snowman.jpb");
			pstmt.setBlob(4, new FileInputStream("D:\\java\\eclipse\\json-20230618.jar"));
			pstmt.setInt(5,  3);
			
			int rows = pstmt.executeUpdate();
			System.out.println("수정된 행 수: "+ rows);
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {}
			}
		}
	}
}