package exam;

import java.util.ArrayList;
import java.util.Iterator;

public class UserTable {
	ArrayList<User> userList;
	int no;
	
	UserTable(){
		userList = new ArrayList<>();
		no = 0;
	}
	
	public User getUser(int no, String id, String pwd, String name) {
		return new User(no, id, pwd, name);
	}
	
	public boolean insert(String id, String pwd, String name) {
		if(findUser(id) == null) {
			no++;
			User newMember = getUser(no, id, pwd, name);
			userList.add(newMember);
			return true;
		}else {
			return false;
		}
	}
	
	public User findUser(String id) {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getId().equals(id)) {
				return userList.get(i);
			}
		}
		return null;
	}
	
	public boolean update(String id, String name, String pwd) {
		User target = findUser(id);
		
		if(target == null) {
			return false;
		}else {
			target.setName(name);
			target.setPwd(pwd);
			return true;
		}
	}
	
	public void printAllUser() {
		System.out.println("번호\t아이디\t비밀번호\t이름");
		Iterator<User> iter = userList.iterator();
		while(iter.hasNext()) {
			User tempUser = iter.next();
			System.out.println(tempUser.getNo() + "\t" + tempUser.getId() + "\t" + tempUser.getPwd() + "\t" + tempUser.getName());
		}
	}
	
	public void printUser(User user) {
		System.out.println("번호 : " + user.getNo());
		System.out.println("아이디 : " + user.getId());
		System.out.println("이름 : " + user.getName());
		System.out.println("비밀번호 : " + user.getPwd());
	}
}
