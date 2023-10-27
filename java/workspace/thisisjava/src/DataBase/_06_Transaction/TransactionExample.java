package DataBase._06_Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionExample {
	public static void main(String[] args) {


		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
			conn.setAutoCommit(false);
			
			String sql1 = "UPDATE accounts SET balance = balance - ? WHERE ano = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, 10000);
			pstmt1.setString(2, "111-111-1111");
			int row1 = pstmt1.executeUpdate();
			if(row1 == 0) throw new Exception("출금되지않음");
			pstmt1.close();
			
			String sql2 = "UPDATE accounts SET balance = balance + ? WHERE ano = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, 10000);
			pstmt2.setString(2, "222-222-2222");
			int row2 = pstmt2.executeUpdate();
			if(row2 == 0) throw new Exception("입금되지않음");
			pstmt2.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {}
			}
		}
	}
}
