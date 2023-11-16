package chapter12;

import lombok.Data;

@Data
public class MemberVO {
	private int no;
	private String name;
	private String id;
	private String filename_org;
	private String filename_real;
	private String password;
	
	private String[] hobbyname;
}
