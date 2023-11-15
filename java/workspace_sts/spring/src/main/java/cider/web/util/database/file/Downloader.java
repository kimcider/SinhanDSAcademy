package cider.web.util.database.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;

import chapter10_utilTest.MemberVO;


/*
 *  jsp에서 다운로드 요청을 수행할 경우 사용
 * 
 * 	Parameter
 * 		FileNameVO에는 
 				realPath
 				uploading_filename
 				saved_filename
 				이 입력된 상태로 들어와야 한다. 
 * 
 *  다운로드 작업이 성공적으로 수행될 경우 true를 리턴
 *  다운로드 작업이 실패할 경우 false를 리턴 
 */

/*  호출 예제
 	@Controller
 	public class MemberController {
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
 */

public class Downloader {
	public boolean download(FileNameVO vo, HttpServletRequest request, HttpServletResponse response) {
		/* 파일 다운로드에 필요한 정보가 충분치 않으면 수행 종료 */
		if(vo.getRealPath() == null) return false;
		if(vo.getUploaded_filename() == null) return false;
		if(vo.getSaved_filename() == null) return false;
		
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(vo.getUploaded_filename(), "utf-8"));
			
			OutputStream out = response.getOutputStream();
			File f = new File(vo.getRealPath() + vo.getSaved_filename());
			FileInputStream in = new FileInputStream(f);
			byte[]buffer = new byte[1024 *8];
			while(true) {
				int count=in.read(buffer);
				if(count == -1) {
					break;
				}
				out.write(buffer, 0, count);
			}
			in.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
}
