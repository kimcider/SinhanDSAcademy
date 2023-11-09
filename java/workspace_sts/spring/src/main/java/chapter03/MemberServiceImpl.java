package chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@AllArgsConstructor
@Setter
public class MemberServiceImpl implements MemberService {
	//이거를 넣어주면!!!!!!! 자동으로 맵핑이된다.
	@Autowired
	//그런데 MemberDAOImpl도 MemberDAO를 구현하고 adminDAOImpl도 memberDAO를 구현한다면
	// 이 경우 어떤 MemberDAO를 주입해야할지 알 수 없다
	// 이 경우 아래처럼 @Qualifier(id명)로 사용하면 구분해 줄 수 있다. 여기서의 id는 @Bean의 아이디겠지
	// 그런데 사실 이렇게 쓸 일이 많이 없다.
	// 신경써서 만들면 이름을 똑같이 만들일이 없어진다 ㅎㅎ. 
	@Qualifier("adminDAO")
	private MemberDAO dao;
	
	
	//이렇게 id 이름이 같다면  객체로 주입을 시켜준다.
	//이러면 appctx에 메소드명이 memberDAO로되어있는애로 주겠지
//	@Autowired
//	private MemberDAO memberDAO;
	
	@Override
	public void listMembers() {
		dao.listMembers();
	}
}
