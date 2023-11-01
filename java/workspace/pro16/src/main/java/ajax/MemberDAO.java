package ajax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	
	MemberDAO(){
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
	public boolean overlappedID(String id) {
		boolean result = false;
		try {
			String query = "select decode(count(*), 1, 'true', 'false') as result from t_member ";
			query+= "where id=?";
			System.out.println("preparedStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
