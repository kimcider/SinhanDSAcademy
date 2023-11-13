package chapter07_myVersion;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentCotroller {
	@Autowired
	private StudentService service;
	
	@GetMapping("/student/index.do")
	public String index(Model model, @RequestParam Map map) {
		model.addAttribute("list", service.search(map));
		return "myStudent/index";
	}	
	
	@GetMapping("/student/index2.do")
	public String index2(Model model, StudentVO vo) {
		System.out.println(vo);
		model.addAttribute("list", service.search2(vo));
		return "myStudent/index";
	}
	
	@GetMapping("/student/view.do")
	public String view(Model model, @RequestParam Integer studno) {
		model.addAttribute("vo", service.view(studno));
		return "student/view";
	}
}
