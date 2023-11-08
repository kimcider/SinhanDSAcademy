package chapter05;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/member")  
public class MemberController {
	
	//Get방식으로 맵핑하기!
	@GetMapping("/member/index.do") //맵핑될 URL // Jsp 경로 http://localhost:8000/test/member/index.do로 접속하면 된다.  여기서 test는 server의 contextPath이다. 처음에 패키지만들때 kr.co.test로 만들어서 ㅇㅇ. 이거 서버 가서 테스트 없애도된다. 
	public String index() {
		System.out.println("index");
		return "member/index";	
		//아까 prefix : /WEB-INF/views/라고했다.
			//<beans:property name="prefix" value="/WEB-INF/views/" />
		//servlet-context.xml에서 prefix가 그렇게 설정되어있고
		//suffix: .jsp라고되어있다.
		
		/*
		 * 	결국 return "ㅎㅎ";라고 해주면
		 *	/WEB-INF/views/ㅎㅎ.jsp로 보내주는것!
		 *
		 *
		 * */
	}
	
	@GetMapping("/test.do")
	public String test() {
		System.out.println();
		return "";
	}
	@PostMapping("/test.do")
	public String test2() {
		System.out.println();
		return "";
	}
	@RequestMapping("/reqesttest.do")
	public String test3() {
		System.out.println();
		return "";
	}
	@RequestMapping(value="/reqesttest.do", method = RequestMethod.GET)
	public String test4() {
		System.out.println();
		return "";
	}
	
	// member/index3.do로 가면 member/index.do로 리다이렉트
	@GetMapping("member/index3.do")
	public String index3() {
		return "redirect:member/index.do";
	}
	
	// void의 경우
	// 요청 url과 js경로가 동일하게 포워딩이 된다. 
	@GetMapping("member/voidTest.do") // /member/test.do가된다.
	public void voidTest() {
		//리턴을 하지 않으면 포워드는
		// /WEB-INF/views/member/test.jsp가나온다. ㅇㅎ
	}
	
	
	
	
	
	
	/* 파라미터 받는거 */
	//1. HttpServletRequest객체 사용
			// http://localhost:8000/test/member/param1.do?id=홍
	@GetMapping("/member/param1.do")
	public String param1(HttpServletRequest request) { //매개변수로 받아와야한다.
		String id=request.getParameter("id");
		System.out.println(id);
		return "member/test";
	}
	
	//requestparam 어노테이션
				//http://localhost:8000/test/member/param2.do?id=%ED%99%8D
	@GetMapping("/member/param2.do")
		//인자에 @RequestParam을 써서 받는것...
		//스프링이 파라미터중에 동일한 이름이 있을 경우 알아서 담아주는것.
		//만약 id가 아니라 name이라고 쓰면
		//	http://localhost:8000/test/member/param2.do?name=%ED%99%8D
		// 파라미터가 없다고 에러가 난다. 
	public String param2(@RequestParam String id) {
		System.out.println(id);
		
		return "member/test";
	}
	
	//만약 아래처럼 어노테이션에 인자를 줘서 파라미터 명을 name이라고 줄 경우
	//http://http://localhost:8000/test/member/param3.do?name=%ED%99%8D 로 접속해도 name을 id로 자동으로 맵핑을해준다.
	@GetMapping("/member/param3.do")
	public String param3(@RequestParam("name") String id) {
		System.out.println(id);
		
		return "member/test";
	}
	@GetMapping("/member/param4.do")
	//이렇게 required를 false로주면 필수가 아니게되서 name변수가 없어도 에러가 나지 않는다.
		//http://localhost:8000/test/member/param4.do?id=%ED%99%8D
	public String param4(@RequestParam(value="name", required=false) String id) {
		System.out.println(id);
		
		return "member/test";
	}
	
	//이렇게 디폴트까지 설정하면 해당 매개변수가 없으면 000을 받는다.
	//http://localhost:8000/test/member/param5.do?id=%ED%99%8D
	@GetMapping("/member/param5.do")
	public String param5(@RequestParam(value="name", required=false, defaultValue = "000") String id) {
		System.out.println(id);
		
		return "member/test";
	}
	
	
	
	
	/* 3. 커맨드 객체로 받아오기. 이거를 제일 많이쓴다. */
	@GetMapping("/param6.do")
	public String param3(MemberVO vo) {
		//http://localhost:8000/test/member/param6.do?id=홍&name=제발 
		//위와 같이 파라미터로 넘겨주면 스프링에서 셋을 알아서 다 해서 vo에 담아준다.
		
		System.out.println(vo);
		return "member/test";
	}
}
