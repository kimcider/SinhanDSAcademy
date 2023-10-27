package sec01.ex01_connectToDB;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date JoinDate;
}
