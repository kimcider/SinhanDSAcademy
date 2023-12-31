package chapter12;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int insertMember(MemberVO vo);
	int insertHobby(HobbyVO vo);
	
	List<MemberVO> list();
	MemberVO login(MemberVO vo);
}
