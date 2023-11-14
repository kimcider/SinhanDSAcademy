package chapter09;

import lombok.Data;

@Data
public class MemberVO {
	private int no;
	private String name;
	private String id;
	
	//Controller에서 서비스로 MemberVO를 넘겨줄 때, Hobby도 넣어주기 위해서 
	//커맨드객체에서 한번에 받을 수 있기 때문에 이렇게 해보자. 
	//커맨드객체는 이름만 똑같으면 배열도 담아주기때문에 ㅇㅇ. 
	private String[] hobbyname;
}
