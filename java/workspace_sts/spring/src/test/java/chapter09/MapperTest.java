package chapter09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {chapter09.MVCConfig.class})
@WebAppConfiguration
@Slf4j
public class MapperTest {
	@Autowired
	MemberMapper mapper;
	
	@Test
	public void insertMember() {
		MemberVO vo = new MemberVO();
		vo.setId("aaa");
		vo.setName("AAA");
		
		assertEquals(1, mapper.insertMember(vo));
	}
	@Test
	public void insertHobby() {
		HobbyVO vo = new HobbyVO();
		vo.setHobbyname("game");
		vo.setMember_no(1);
		
		assertEquals(1, mapper.insertHobby(vo));
	}
	
	
	@Autowired
	MemberService service;
	
	//hobby를 insert하기 전의 insert테스트
	@Test
	public void serviceTest_1() {
		MemberVO vo = new MemberVO();
		vo.setId("bbb");
		vo.setName("BBB");
		
		service.insert(vo);
	}
	
	
	//hobby를 insert한 후의 insert테스트
	@Test
	public void serviceTest_2() {
		MemberVO vo = new MemberVO();
		vo.setId("ccc");
		vo.setName("CCC");
		vo.setHobbyname(new String[] {"독서", "영화", "널뛰기"});
		service.insert(vo);
	}
}
