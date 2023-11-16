package chapter12;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	@GetMapping("/test2")
	public MemberVO test2() {
		MemberVO vo = new MemberVO();
		vo.setName("홍");
		vo.setId("길동");
		return vo;
	}
	@GetMapping("/test3")
	public List test3() {
		MemberVO vo = new MemberVO();
		vo.setName("홍길동");
		vo.setId("hong");
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		list.add(vo);
		
		vo = new MemberVO();
		vo.setName("김병천");
		vo.setId("kim");
		list.add(vo);
		
		return list;
	}
	
	//매퍼 주입받고
	@Autowired
	private MemberMapper mapper;
	@GetMapping("/test4")
	public List<MemberVO> all(){
		return mapper.list();
	}
	
}
