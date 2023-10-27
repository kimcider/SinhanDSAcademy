package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//여기까지는 똑같다
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		System.out.println("아이디: " + user_id);
		System.out.println("비밀번호: " + user_pw);
		
		//배열일때
		// 사용자가 아무것도 입력하지 않을 경우 에러가난다.
		//이렇게 try~catch로 묶어도되고, 
		/*
		try {
			String[] subject = request.getParameterValues("subject");
			for(int i = 0; i < subject.length; i++) {
				System.out.println("선택한 과목: " + subject[i]);
			}			
		}catch(Exception e) {
			System.out.println(e);
		}
		*/
		
		/*
		String[] subject = request.getParameterValues("subject");
		//이렇게 조건문으로 묶어도된다.
		if(subject == null) return;
		for(int i = 0; i < subject.length; i++) {
			System.out.println("선택한 과목: " + subject[i]);
		}*/
		
		String[] subject = request.getParameterValues("subject");
		for(String s : subject) {
			System.out.println("선택한 과목: " + s);
		}	
		
	}
}
