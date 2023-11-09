package chapter05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* 본래 인터페이스 구현해서 만들어야 하는데 일단은 시간없으니 그냥 바로 만들어서 사용한다*/
@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	public MemberVO getMember(MemberVO vo) {
		return dao.getMember(vo);
	}
}
