package chapter10_utilTest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int insertMember(MemberVO vo);
	int insertHobby(HobbyVO vo);
	
	List<MemberVO> list();
}
