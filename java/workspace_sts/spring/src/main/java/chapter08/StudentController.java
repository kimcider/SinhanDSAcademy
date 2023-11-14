package chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j //이거를 쓰면 log라는 필드가 자동으로 생성이된다.
public class StudentController {
	@Autowired
	private StudentService service;
	
	@GetMapping("/student/search2.do")
	private String index3(Model model, StudentVO vo) {
		model.addAttribute("list", service.search2(vo));
		model.addAttribute("total", service.count(vo));
		log.debug("logTest");
		return "student/index";
	}
}