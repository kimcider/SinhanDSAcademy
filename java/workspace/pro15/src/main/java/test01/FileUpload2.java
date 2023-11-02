package test01;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/upload2.do")
public class FileUpload2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String realPath = request.getRealPath("/upload2");
		System.out.println(realPath);
		int maxSize = 1024 * 1024;
		String encoding = "utf-8";
		
		try {
			//file rename policy를 넣어주면 이름이 중복되는 파일이 있을 경우 파일명을 바꿔줄 수 있다.
//			MultipartRequest mr = new MultipartRequest(request, realPath, maxSize, encoding, new FileRenamePolicy() {
//				
//				@Override
//				public File rename(File arg0) {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			});
			
			// defaultFileRenamePolicy를 넣어주면 알아서 파일명을 바꿔준다. 
			MultipartRequest mr = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
			//이런식으로 원본 파일 명과 실제 저장된 파일 명을 출력할 수 있다. 
			String file1 = mr.getFilesystemName("file1");
			System.out.println("저장된 파일 명: " + file1);
			String org = mr.getOriginalFileName("file1");
			System.out.println("원본 파일 명: " + org);

			//파라미터
			//파라미터를 굉장히 쉽게 받아올 수 있다.
			String param1 = mr.getParameter("param1");
			System.out.println("param1: " + param1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
