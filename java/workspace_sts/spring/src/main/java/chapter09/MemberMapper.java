package chapter09;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int insertMember(MemberVO vo);
	int insertHobby(HobbyVO vo);
}
