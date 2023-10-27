package sec01.ex04_maintenanceMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DAO는 본래는 싱글톤으로 해야한다.
public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	private void connDB() {
		try {			
			//datasource 불러오기
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env"); //여기까지는 경로를 바꾸면 안된다.
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle"); //context.xml의 리소스에 넣었던 그 네임을 넣어야한다. 
			con = dataFactory.getConnection();
			
			// statement 객체 생성
			stmt = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<>();
		try {
			connDB(); //DB접속
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
			
			// close할때는 나중에 만들어진 것을 먼저 close한다.
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addMember(MemberVO memberVO){
		try {
			connDB();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String query = "insert into t_member (id, pwd, name, email) values (?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			pstmt.close();
			stmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeMember(String inputID, String inputPWD) {
		try {
			connDB();
			
			String query = "select id, pwd from t_member where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, inputID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String targetPWD = rs.getString("pwd");
				
				//입력한 비밀번호와 실제 DB의 비밀번호가 일치하면 삭제
				if(targetPWD.equals(inputPWD)) {
					query = "delete from t_member where id=?";
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, inputID);
					
					pstmt.executeUpdate();									
				}
			}
			pstmt.close();
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMember(MemberVO memberVO) {
		try {
			connDB();
			String query = "select id, pwd from t_member where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberVO.getId());
			ResultSet rs = pstmt.executeQuery();
			//수정할 요소가 없으면 함수 수행 종료
			if(!rs.next()) {return;}
			
			if(memberVO.getPwd().length() != 0 & !"".equals(memberVO.getPwd())){
				query = "update t_member set pwd=? where id=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, memberVO.getPwd());
				pstmt.setString(2, memberVO.getId());
				
				pstmt.executeUpdate();
			}
			if(memberVO.getName().length() != 0 & !"".equals(memberVO.getName())){
				query = "update t_member set name=? where id=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, memberVO.getName());
				pstmt.setString(2, memberVO.getId());
				
				pstmt.executeUpdate();
			}
			if(memberVO.getEmail().length() != 0 & !"".equals(memberVO.getEmail())){
				query = "update t_member set email=? where id=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, memberVO.getEmail());
				pstmt.setString(2, memberVO.getId());
				
				pstmt.executeUpdate();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}