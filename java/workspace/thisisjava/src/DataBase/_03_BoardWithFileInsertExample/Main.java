package DataBase._03_BoardWithFileInsertExample;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			
			String sql = "INSERT INTO boards "
					+ "(bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledate) "
					+ "VALUES (SEQ_BNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";
			
//			String[] col = {"bno"};
//			PreparedStatement pstmt = conn.prepareStatement(sql, col);
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
			
			pstmt.setString(1,  "눈오는날");
			pstmt.setString(2, "ㅎㅁ박눈이내려용");
			pstmt.setString(3, "winter");
			pstmt.setString(4, "snow.jpg");
			pstmt.setBlob(5, new FileInputStream("D:\\java\\eclipse\\json-20230618.jar"));
			
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 갯수: " + rows);
			
			if(rows > 0) { //정상적으로 삽입이 됐을 경우
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("저장된 bno : " + bno);
				}
				rs.close();
			}
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