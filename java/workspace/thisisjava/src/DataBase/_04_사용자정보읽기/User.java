package DataBase._04_사용자정보읽기;

import lombok.Data;

@Data
public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail;
}
