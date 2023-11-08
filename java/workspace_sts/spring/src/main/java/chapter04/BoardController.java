package chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Component
@Controller
public class BoardController {
	
	//서비스는 주입받아야하니까. 
	@Autowired
	private BoardService service;
	
	public void list() {
		service.selectArticles();
	}
}
