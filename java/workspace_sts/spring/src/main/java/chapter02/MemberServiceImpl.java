package chapter02;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	
//	public void setMemberDAO(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
	//setMembersDAO로 dao를 주입해주지 않고 아래 함수를 수행하면 널포인트 에러가난다. 
	@Override
	public void listMembers() {
		memberDAO.listMembers();
	}

}
