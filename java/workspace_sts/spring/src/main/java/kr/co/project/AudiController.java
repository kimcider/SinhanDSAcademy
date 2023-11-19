package kr.co.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/audi")
public class AudiController {
	@GetMapping("/index.do")
	public String index() {
		System.out.println("nono");
		return "audi/index";
	}
	@GetMapping("/howToCome.do")
	public String howToCome() {
		return "audi/howToCome";
	}
}
