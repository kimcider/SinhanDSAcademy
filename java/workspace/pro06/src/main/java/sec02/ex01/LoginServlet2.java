package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// MIME type과 인코딩형식 지정
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		String data = "<html>";
		data += "<body>";
		data += "아이디: " + user_id;
		data += "<br>";
		data += "패스워드: " + user_pw;
		data += "</body>";
		data += "</html";
		
		out.print(data);
		
		//이렇게 해도 된다.
//		out.print("<html>");
//		out.print("<body>");
//		out.print("아이디: " + user_id);
//		out.print("<br>");
//		out.print("패스워드: " + user_pw);
//		out.print("</body>");
//		out.print("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
