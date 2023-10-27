package testExample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String id = request.getParameter("user_id");
		id = id.trim();
		System.out.println(id);
		if(id == null || id.length() == 0 || "".equals(id)) {
			pw.print("<html>");
			pw.print("<body>");
			pw.print("아이디를 입력하세요!!<br>");
			pw.print("<a href='http://localhost:8000/pro06/testExample/login.html'>로그인창으로 이동</a>");
			pw.print("</body>");
			pw.print("</html>");
		}else {
			pw.print("<html>");
			pw.print("<body>");
			pw.print(id + " 님!! 로그인하셨습니다.");
			pw.print("</body>");
			pw.print("</html>");
		}
	}
}
