package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");

		System.out.println("아이디: " + user_id);
		System.out.println("비밀번호: " + user_pw);
	}
	
	
	/*
	 * 
	 * doPost함수가 존재하지 않으면 post로 전송 자체가 불가능하다!!!!!!!
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/*
	 * 본래 같은 url을 사용할 수는 없지만, 특정 사이트에서는 get으로 전달하고, 
	 * 다른 사이트에서는 post로 전달하게 하려고 한다면 같은 url을 줘도 서로 다르게 동작하게 만들 수 있다.  
	 * */
}
