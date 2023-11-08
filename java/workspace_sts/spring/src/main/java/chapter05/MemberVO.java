package chapter05;

import lombok.Data;

@Data
/* VO는 싱글톤으로 할 게 아니라서 여기다 어노테이션을 쓰면 안된다 !!*/
public class MemberVO {
	private String id;
	private String name;
	
}
