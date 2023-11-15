package chapter10;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
