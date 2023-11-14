package chapter09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/member/regist.do")
	public String regist() {
		return "member/write";
	}
	
	@PostMapping("/member/regist.do")
	public String insert(MemberVO vo) {
		service.insert(vo);
		return "redirect:regist.do";
	}
}
