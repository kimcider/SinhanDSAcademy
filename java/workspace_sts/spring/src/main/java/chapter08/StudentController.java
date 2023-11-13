package chapter08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
	@Autowired
	private StudentService service;
	
	@GetMapping("/student/search2.do")
	private String index3(Model model, StudentVO vo) {
		model.addAttribute("list", service.search2(vo));
		model.addAttribute("total", service.count(vo));
		
		return "student/index";
	}
}