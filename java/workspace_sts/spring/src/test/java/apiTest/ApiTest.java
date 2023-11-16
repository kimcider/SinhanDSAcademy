package apiTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import chapter12.MemberVO;

public class ApiTest {

	@Test
	public void test() throws Exception{
		// Object -> json(text)
		MemberVO vo = new MemberVO();
		vo.setName("홍길동");
		vo.setId("hong");
		
		//object를 맵핑시켜주는놈
		ObjectMapper om = new ObjectMapper();
		
		//여기에 vo객체를 넘기면 이 vo를 스트링형식으로 반환해줌
		String text = om.writeValueAsString(vo);
		System.out.println(text);
		
		
		// json(text) -> Object
		MemberVO obj = om.readValue(text, MemberVO.class);
		System.out.println(obj);
	}
	@Test
	public void listTest() throws Exception{
		// Object -> json(text)
		MemberVO vo = new MemberVO();
		vo.setName("홍길동");
		vo.setId("hong");
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		list.add(vo);
		
		vo = new MemberVO();
		vo.setName("김병천");
		vo.setId("kim");
		list.add(vo);
		
		//object를 맵핑시켜주는놈
		ObjectMapper om = new ObjectMapper();
		
		String text = om.writeValueAsString(list);
		System.out.println(text);
		
		
		// json(text) -> Object
		ArrayList<MemberVO> obj = om.readValue(text, new TypeReference<ArrayList<MemberVO>>() {
			//TypeReference가 generic interface이기 떄문에
			//익명 객체로 구현한것
		});
		System.out.println(obj);
	}
}
