package kr.co.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import chapter08.StudentMapper;
import chapter08.StudentService;
import chapter08.StudentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {chapter08.MVCConfig.class})
@WebAppConfiguration
public class StudentTest {
	//매퍼 테스트
	@Autowired
	StudentMapper mapper;
	
	@Test
	public void countTest() {
		System.out.println(mapper.count(new StudentVO()));
	}
	
	@Test
	public void listTest() {
		System.out.println(mapper.search2(new StudentVO()));
	}
	@Test
	public void search() {
		StudentVO vo = new StudentVO();
		vo.setSearchType("name");
		vo.setSearchWord("훈");
		System.out.println(mapper.search2(vo));
	}
	
	//서비스 테스트
	@Autowired
	StudentService service;
	@Test
	public void search2Test() {
		StudentVO vo = new StudentVO();
		vo.setSearchType("name");
		vo.setSearchWord("훈");
		System.out.println("서치2: " + service.search2(vo));
	}
	
	//컨트롤러 테스트
	@Autowired
	WebApplicationContext ctx; //얘는 가상으로 실행할 때 주소를 받아오기위해서사용
	MockMvc mock;//얘도 그냥 가상으로 만든 MVC
	//먼저 메소드 앞에서 실행이 되야해서
	@Before
	public void before() {
		//얘로 이렇게 작업을 해줘야한다.
		//mock은 직접 색성할 수 없고 이렇게 빌더객체를생성해서만들어야함.
		mock = MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	
	//이제 테스트해보고싶은 메소드를 만든다
	@Test
	public void search3() throws Exception{
		RequestBuilder req = MockMvcRequestBuilders.get("/student/search2.do");
		mock.perform(req);
	}
	
	
	//파라미터가 있는 경우(페이지, 검색어)
	@Test
	public void search32() throws Exception{
		RequestBuilder req = MockMvcRequestBuilders.get("/student/search2.do")
							.param("searchType", "name")
							.param("searchWord", "훈");
		mock.perform(req);
	}
}
