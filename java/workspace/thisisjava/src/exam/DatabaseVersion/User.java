package exam.DatabaseVersion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
	private int no;
	private String id;
	private String pwd;
	private String name;
}
