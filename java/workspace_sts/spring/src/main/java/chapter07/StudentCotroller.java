package chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentCotroller {
	@Autowired
	private StudentService service;
	
	//만약 다른 서비스를 또 주입받고싶으면
	/*
	 * @Autowired
	 * private StudentService service2;
	 * 
	 * 이런식으로 넣어주면 된다 ㅎㅎ. 
	 * 쓸떄도 serivce2.메소드저쩌구() 이런식으로 ㅇㅇ. 
	 */
	
	
	@GetMapping("/student/index.do")
	public String index(Model model) {
		model.addAttribute("list", service.all());
		return "student/index";
	}
}
