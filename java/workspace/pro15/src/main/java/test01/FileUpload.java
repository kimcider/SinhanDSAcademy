package test01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
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
		String encoding = "utf-8";
		
		//톰캣의 가상의 경로 안에서 구동하고자 한다면 
//		String realPath = request.getRealPath("test01");
//		System.out.println(realPath);
//		File currentDirPath = new File(realPath);
		
		//첨부파일을 저장할 경로를 지정.
		File currentDirPath = new File("D:\\java\\workspace\\pro15\\upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);	// 최대 파일 크기 설정 (1MB)
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//request객체에서 매개변수를 list로 가져온다. 
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				//파일 업로드 창에서 업로드된 항목들을 하나씩 가져온다. 
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					// uploadForm의 field일 경우 출력
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				} else {
					// uploadForm의 field가 아닐 경우, 즉 첨부파일일 경우!!
					System.out.println("매개변수이름: " + fileItem.getFieldName());
					System.out.println("파일이름: " + fileItem.getName());
					System.out.println("파일 크기: " + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						//다운받을 경로, 파일명 설정.
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						//첨부파일을 내 컴퓨터에 저장
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
