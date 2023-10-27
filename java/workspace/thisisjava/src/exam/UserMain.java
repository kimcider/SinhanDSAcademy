package exam;

import java.util.Scanner;

public class UserMain {
	public static void main(String[] args) {
		UserTable userTable = new UserTable();
		Scanner scanner = new Scanner(System.in);
		String id = null;
		String pwd = null; 
		String name = null;
		User target = null;
		while(true) {
			System.out.println("1.회원 등록 | 2. 회원목록 | 3.회원상세 | 4.회원수정 | 5.종료");
			System.out.print("입력]");
			int n = Integer.valueOf(scanner.nextLine());
			
			
			switch(n) {
				case 1:
					System.out.println("[회원등록]");
					System.out.print("아이디:");
					id = scanner.nextLine();
					System.out.print("비밀번호:");
					pwd = scanner.nextLine();
					System.out.print("이름:");
					name = scanner.nextLine();
					
					if(userTable.insert(id, pwd, name) == false) {
						System.out.println("중복된 아이디입니다.");
					}else {
						System.out.println("회원등록 성공");
					}
					break;
				case 2:
					System.out.println("[회원목록]");
					userTable.printAllUser();
					break;
				case 3:
					System.out.println("[회원상세]");
					System.out.print("아이디:");
					id = scanner.nextLine();
					target = userTable.findUser(id);
					if(target != null) {
						userTable.printUser(target);
					}else {
						System.out.println("해다 아이디가 존재하지 않습니다.");
					}
					
					break;
				case 4:
					System.out.println("[회원수정]");
					System.out.print("아이디:");
					id = scanner.nextLine();
					System.out.print("비밀번호:");
					pwd = scanner.nextLine();
					System.out.print("이름:");
					name = scanner.nextLine();
					
					target = userTable.findUser(id);
					if(target != null) {
						userTable.update(id, name, pwd);
						System.out.println("정상적으로 수정되었습니다.");
					}else {
						System.out.println("해당 아이디가 존재하지 않습니다.");
					}
					
					break;
				case 5:
					System.out.println("프로그램을 종료합니다.");
					scanner.close();
					return;
			}
		}
	}
}
