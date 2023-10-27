package member;

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
	private Date JoinDate;
}
