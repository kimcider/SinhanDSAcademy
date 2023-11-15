package chapter10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Transactional(rollbackFor = Exception.class)
	public boolean insert(MemberVO mvo) {
		int result = mapper.insertMember(mvo);
		
		if(result > 0) {
			HobbyVO hvo = new HobbyVO();
			hvo.setMember_no(mvo.getNo());
			for(String hobbyname : mvo.getHobbyname()) {
				hvo.setHobbyname(hobbyname);
				mapper.insertHobby(hvo);
			}
			return true;
		}
		return false;
	}
	
	public List<MemberVO> list(){
		return mapper.list();
	}
}
