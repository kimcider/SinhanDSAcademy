package chapter10_utilTest;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cider.web.util.database.file.Downloader;
import cider.web.util.database.file.FileNameVO;
import cider.web.util.database.file.Uploader;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/member/index.do")
	public String index(Model model) {
		model.addAttribute("list", service.list());
		return "member/list";
	}
	
	@GetMapping("/member/regist.do")
	public String regist() {
		return "member/write";
	}
	
	/* 파일 업로드 예제 */
	@PostMapping("/member/regist.do")
	public String insert(MemberVO vo, @RequestParam MultipartFile filename, HttpServletRequest request) {
		Uploader uploader = new Uploader();
		FileNameVO fvo = new FileNameVO(request.getRealPath("/upload/"));
		fvo = uploader.upload(fvo, filename);
		
		vo.setFilename_org(fvo.getUploaded_filename());
		vo.setFilename_real(fvo.getSaved_filename());
		
		service.insert(vo);
		return "redirect:regist.do";
	}

	/* 파일 다운로드 예제 */
	@GetMapping("member/download.do")
	public String download(MemberVO vo, HttpServletRequest request, HttpServletResponse response) {
		Downloader downloader = new Downloader();
		String uploaded_filename = vo.getFilename_org();
		String saved_filename = vo.getFilename_real();
		FileNameVO fvo = new FileNameVO(request.getRealPath("/upload/"), uploaded_filename, saved_filename);
		if(downloader.download(fvo, request, response)) {
			System.out.println("다운로드 완료!");
			return null;
		}else {
			System.out.println("다운로드 실패!");
			return "redirect:index.do";
		}
	}
}
