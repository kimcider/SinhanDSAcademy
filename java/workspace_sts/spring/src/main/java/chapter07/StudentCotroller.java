package chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentCotroller {
	@Autowired
	private StudentService service;
	
	@GetMapping("/student/index.do")
	public String index(Model model) {
		model.addAttribute("list", service.all());
		return "student/index";
	}
}
