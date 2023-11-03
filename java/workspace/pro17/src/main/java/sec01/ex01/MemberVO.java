package sec01.ex01;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	MemberVO(String id, String pwd, String name, String email){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
}
