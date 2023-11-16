package chapter11;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/member/regist.do")
	public String regist() {
		return "member/write";
	}
	
	
	@PostMapping("/member/regist.do")
	public String insert(MemberVO vo, @RequestParam MultipartFile filename, HttpServletRequest request) {
		/* insert 전 파일명 변경 후 set */
		if(filename.isEmpty() == false) {
			//본래 파일 명
			String org = filename.getOriginalFilename();
			//확장자
			String ext = org.substring(org.lastIndexOf("."));
			//실제 파일 명
			String real = System.currentTimeMillis() + ext;  
			
			System.out.println("originalname: " + org);
			System.out.println("realname: " + org);
			
			//파일 저장
			String path = request.getRealPath("/upload/") + real;
			try {
				/* 파일 이전 */
				filename.transferTo(new File(path));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			vo.setFilename_org(org);
			vo.setFilename_real(real);
		}
		service.insert(vo);
		return "redirect:regist.do";
	}
	
	@GetMapping("/member/index.do")
	public String index(Model model) {
		model.addAttribute("list", service.list());
		return "member/list";
	}
	
	@GetMapping("/member/login.do")
	public String login() {
		return "member/login";
	}
	
	/* 원래 로그인을 하면 기존에 보던 그 사이트로 돌려줘야하는데 그러려면 이전 사이트 주소도 필요함! */
	/* 로그인에 성공하면 세션을 저장할거기 때문에 세션도 필요 */
	@PostMapping("/member/login.do")
	public String login(MemberVO vo, HttpSession sess, HttpServletResponse response) throws Exception{
		//로그인 시도
		MemberVO login = service.login(vo);
		
		//로그인 성공
		if(login != null) { 
			//로그인 성공시 session에 저장
			sess.setAttribute("loginSess", login);
			return "redirect:index.do";
		}
		//로그인 실패
		else { 
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('아이디/비밀번호가 올바르지 않습니다.');");
			out.print("location.href='login.do';");
			out.print("</script>");
			out.close();
			return null;
		}
	}
}
