package chapter09;

import lombok.Data;

@Data
public class HobbyVO {
	private int no;
	private String hobbyname;
	//DB의 컬럼과 맵핑을 시키는 표기법은 모두 snake표기법으로하신다!
	private int member_no;
}
