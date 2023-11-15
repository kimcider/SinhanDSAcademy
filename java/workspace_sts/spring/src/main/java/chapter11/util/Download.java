package chapter11.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import chapter10.MemberVO;

@Controller
public class Download {
	@GetMapping("member/download.do")
	public void download(MemberVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(vo.getFilename_org() == null) return;
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(vo.getFilename_org(), "utf-8"));
		
		OutputStream out = response.getOutputStream();
		String realPath = request.getRealPath("/upload/");
		String fileName = realPath + vo.getFilename_real();
		File f = new File(fileName);
		FileInputStream in = new FileInputStream(f);
		byte[]buffer = new byte[1024*8];
		while(true) {
			int count=in.read(buffer);
			if(count == -1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();	
		
		//이거 쓰면 에러나네
		//OutputStream out = response.getOutputStream();
			//이걸 하면 아래꺼를 쓰면 안되나보다. 
//		return "member/index.do";
	}
}
