package DataBase.게시판만들기;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BoardExample {
	static Connection conn = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static Scanner scanner = new Scanner(System.in);
	
	static int bno;
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234");
			
			getBno();
			
			list();
			
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

	static void getBno() {
		String sql = "SELECT bno FROM(SELECT bno FROM boards ORDER BY bno DESC) WHERE rownum = 1";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				bno = rs.getInt("bno") + 1;				
			}else {
				bno = 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();	
			}catch(Exception e) {}
		}
	}
	
	public static void list() {
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("----------------------------");
		System.out.println("no\twriter\tdate\t\ttitle");
		System.out.println("----------------------------");
		
		String sql = "SELECT bno, bwriter, bdate, btitle " +
				"FROM boards";
		
		//print all contents in boards
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("bno") + "\t" + rs.getString("bwriter") + "\t" + rs.getDate("bdate") + "\t" + rs.getString("btitle"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();	
			}catch(Exception e) {}
		}
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		
		int number = Integer.valueOf(scanner.nextLine());
		
		switch(number) {
			case 1:
				create();
				break;
			case 2:
				read();
				break;
			case 3:
				clear();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				return;
		}
		list();
	}
	
	
	static void create() {
		System.out.println("[새 게시물 입력]");
		String title, content, writer;
		System.out.print("제목: ");
		title = scanner.nextLine();
		System.out.print("내용: ");
		content = scanner.nextLine();
		System.out.print("작성자: ");
		writer = scanner.nextLine();
		
		Board target = new Board();
		target.setBno(bno);
		target.setBtitle(title);
		target.setBdate(new java.sql.Date(System.currentTimeMillis()));
		target.setBcontent(content);
		target.setBwriter(writer);
		
		System.out.println("----------------------------");
		System.out.println("보조 메뉴: 1.OK | 2.Canel");
		System.out.print("메뉴 선택: ");
		if(Integer.valueOf(scanner.nextLine()) == 1) {
			String sql = "INSERT INTO boards "
					+ "(bno, bwriter, bdate, btitle, bcontent) "
					+ "VALUES (?, ?, ?, ?, ?)";
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				int index = 1;
				pstmt.setInt(index++, target.getBno());
				pstmt.setString(index++, target.getBwriter());
				pstmt.setDate(index++, target.getBdate());
				pstmt.setString(index++, target.getBtitle());
				pstmt.setString(index++, target.getBcontent());
				
				int rows = pstmt.executeUpdate();
				if(rows == 1) {
					bno++;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
			
		}
	}
	
	static void read() {
		System.out.println("[게시물 읽기]");
		System.out.print("bno: ");
		int num = Integer.valueOf(scanner.nextLine());
		
		String sql = "SELECT * FROM boards WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next() == true) {
				Board target = new Board();
				target.setBno(rs.getInt("bno"));
				target.setBtitle(rs.getString("btitle"));
				target.setBcontent(rs.getString("bcontent"));
				target.setBwriter(rs.getString("bwriter"));
				target.setBdate(rs.getDate("bdate"));
				
				System.out.println("##########");
				System.out.println("번호: " + target.getBno());
				System.out.println("제목: " + target.getBtitle());
				System.out.println("내용: " + target.getBcontent());
				System.out.println("작성자: " + target.getBwriter());
				System.out.println("날짜: " + target.getBdate());
				System.out.println("----------------------------");
				
				System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
				System.out.print("메뉴 선택");
				switch(Integer.valueOf(scanner.nextLine())) {
				case 1:
					update(target);
					break;
				case 2:
					delete(target);
					break;
				case 3:
					return;
				}
				
			}else {
				System.out.println("그런 게시물 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
			}catch(Exception e) {}
		}
	}
	
	static void update(Board target) {
		System.out.println();
		System.out.println("[수정 내용 입력]");
		String title, content, writer;
		System.out.print("제목: ");
		title = scanner.nextLine();
		System.out.print("내용: ");
		content = scanner.nextLine();
		System.out.print("작성자: ");
		writer = scanner.nextLine();
		
		System.out.println("보조 메뉴: 1.OK | 2.Cancel");
		System.out.print("메뉴 선택: ");
		int check = Integer.valueOf(scanner.nextLine());
		if(check == 1) {
			String sql = "UPDATE boards SET "
					+ "btitle = ?, bcontent = ?, bwriter = ? "
					+ "WHERE bno = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				int index = 1;
				pstmt.setString(index++, title);
				pstmt.setString(index++, content);
				pstmt.setString(index++, writer);
				pstmt.setInt(index, target.getBno());
				int rows = pstmt.executeUpdate();
				if(rows == 1) {
					System.out.println("수정 성공");
				}else {
					System.out.println("수정 실패");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}			
		}else {
			System.out.println("수정 취소");
		}
		
	}
	static void delete(Board target) {
		String sql = "DELETE FROM boards WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, target.getBno());
			int rows = pstmt.executeUpdate();
			if(rows == 1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch(Exception e) {}
		}
	}
	
	static void clear() {
		System.out.println("[게시물 전체 삭제]");
		System.out.println("----------------------------");
		System.out.println("보조 메뉴: 1.OK | 2.Cancel");
		System.out.print("메뉴 선택: ");
		int check = Integer.valueOf(scanner.nextLine());
		if(check == 1) {
			String sql = "DELETE FROM boards";
			try {
				pstmt = conn.prepareStatement(sql);
				int rows = pstmt.executeUpdate();
				if(rows > 0) {
					System.out.println(rows + "개의 개시글 전부 삭제");
				}else {
					System.out.println("삭제 실패");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
				}catch(Exception e) {}
			}
		}
	}
}
