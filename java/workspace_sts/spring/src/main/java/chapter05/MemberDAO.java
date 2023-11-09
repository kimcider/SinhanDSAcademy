package chapter05;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	public MemberVO getMember(MemberVO vo) { 
		vo.setName("홍길동");
		return vo;
	}
}
