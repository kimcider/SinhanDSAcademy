package exam.DatabaseVersion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Database {
	Connection conn = null;
	
	public Database() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			//DB 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
			System.out.println("커넥션생성완료");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		}
	}
	public void closeDatabase() {
		try {
			conn.close();
		}catch(Exception e) {
			
		}
	}
	
	public User getUser(int no, String id, String pwd, String name) {
		return new User(no, id, pwd, name);
	}
	
	//DB에서 가장 큰 no값을 가져와서 거기서 1을 더한 값을 리턴
	public int getNextNo() {
		String sql = "SELECT max(no) FROM useraccount";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("max(no)");
			}
		}catch(Exception e) {}
		return result + 1;
	}
	
	public boolean insert(String id, String pwd, String name) {
		if(findUser(id) == null) {
			int no = getNextNo();

			String sql = "INSERT INTO userAccount (no, id, pwd, name) VALUES (?, ?, ?, ?)";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no++);
				pstmt.setString(2, id);
				pstmt.setString(3, pwd);
				pstmt.setString(4, name);
				
				int rows = pstmt.executeUpdate();
				if(rows > 0) return true;
				else return false;
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return false;
	}
	
	public User findUser(String id) {
		String sql = "SELECT * FROM useraccount WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("있음");
				return new User(rs.getInt("no"), rs.getString("id"), rs.getString("pwd"), rs.getString("name"));
			}else {
				System.out.println("사용자아이디존재x");
				return null;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
/*
 * UPDATE dept_temp2
SET
	loc = 'SEOUL'
WHERE deptno = (SELECT deptno
								FROM dept_temp2
								WHERE dname = 'OPERTIONS');
 * 
 * */	
	public void update(User target, String name, String pwd) {		
		String sql = "UPDATE useraccount "
				+ "SET name = ?, pwd = ? "
				+ "WHERE no = 2";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void printAllUser() {
		System.out.println("번호\t아이디\t비밀번호\t이름");
		String sql = "SELECT no, id, pwd, name FROM useraccount";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("no") + "\t" + rs.getString("id") + "\t" + rs.getString("pwd") + "\t" + rs.getString("name"));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void printUser(User user) {
		String sql = "SELECT no, id, pwd, name FROM useraccount WHERE no = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getNo());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("번호 : " + rs.getInt("no"));
				System.out.println("아이디 : " + rs.getString("id"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("비밀번호 : " + rs.getString("pwd"));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
