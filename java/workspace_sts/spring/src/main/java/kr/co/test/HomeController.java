package kr.co.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//어노테이션 넣고
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//요청이 들어오면 어떤 주소로 올지를 적어두고
	//스프링이 get방식으로 이 주소로 접속을 하면 이 home메소드가 실행된다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//담아줄거 담아준 후에, 
		model.addAttribute("serverTime", formattedDate );
		
		//홈을 리턴하면 webapp/web-inf/views/home.jsp를 포워딩시켜준다. 
		return "home";
	}
	
}
