package exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
	private int no;
	private String id;
	private String pwd;
	private String name;
}
