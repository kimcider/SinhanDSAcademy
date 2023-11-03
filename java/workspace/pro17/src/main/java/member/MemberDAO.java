package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	public MemberDAO(){
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

	public List listMembers() {
		List memberList = new ArrayList();
		try {
			String query = "select * from t_member order by id";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setJoinDate(rs.getDate("joinDate"));
				
				memberList.add(vo);
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return memberList;
	}
	
	public List listMembers(MemberVO memberVO) {
		List memberList = new ArrayList();
		String _name = memberVO.getName();
		try {
			String query = "select * from t_member ";
			if((_name != null && _name.length() != 0)) {
				query += " where name = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, _name);
			}else {
				pstmt = con.prepareStatement(query);
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setJoinDate(rs.getDate("joinDate"));
				
				memberList.add(vo);
			}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return memberList;
	}
	
	public MemberVO findMember(String id) {
		MemberVO memInfo = null;
		try {
			String query = "select * from t_member ";
			query+= "where id=?";
			System.out.println("preparedStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				memInfo = new MemberVO(rs.getString("id"), 
										rs.getString("pwd"), 
										rs.getString("name"), 
										rs.getString("email"),
										rs.getDate("joinDate"));
			}
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}
	
	public void addMember(MemberVO m) {
		try {
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "INSERT INTO t_member(id, pwd, name, email)" + "VALUES(?, ?, ?, ?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			//등록/삭제/수정은 등록이 된 갯수를 리턴한다. 때문에 여기서 받아온 값을 int로 리턴받아서 사용하는 경우도 있다.
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modMember(MemberVO m) {
		try {
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "UPDATE t_member SET pwd=?, name=?, email=? WHERE id=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			
			//등록/삭제/수정은 등록이 된 갯수를 리턴한다. 때문에 여기서 받아온 값을 int로 리턴받아서 사용하는 경우도 있다.
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id) {
		try {
			String query = "DELETE FROM t_member WHERE id=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			//등록/삭제/수정은 등록이 된 갯수를 리턴한다. 때문에 여기서 받아온 값을 int로 리턴받아서 사용하는 경우도 있다.
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}