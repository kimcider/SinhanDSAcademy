package chapter09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberMapper mapper;
	
	public boolean insert(MemberVO mvo) {
		System.out.println("등록 전 no: " + mvo.getNo()); //null일꺼고
		int result = mapper.insertMember(mvo); //이렇게 하면 회원이 등록은 되긴 하는데
		System.out.println("등록 후 no: " + mvo.getNo()); //등록된 pk값일것이다.
		
		//취미 등록
		//여기서 취미가 한개가 아닐 수도 있으니 배열로 값을 받아야한다.
		//그래서 2개를 작업해야한다. 
		//하나는 취미명 갯수만큼 반복시켜야하고
		//두번째는 회원의 pk도 가져와서 저장
			//근데 이게문제지. 방금 저장한놈의 PK를 어떻게 가져올 것인가
			//이때 MemberMapper로가서 selectKey를 추가해줘야한다.
		
		if(result > 0) {
			HobbyVO hvo = new HobbyVO();
			hvo.setMember_no(mvo.getNo());
			//반복시키면된다.
			for(String hobbyname : mvo.getHobbyname()) {
				hvo.setHobbyname(hobbyname);
				mapper.insertHobby(hvo);
			}
		}
		
		return true;
	}
}
