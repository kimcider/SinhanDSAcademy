package _03_loginUsingSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowMember
 */
@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String id = "", pwd = "";
		Boolean isLogon = false;

		// 세션 객체 받아오기
		HttpSession session = request.getSession();

		// 로그인을 하지 않을 경우 isLogon 속성이 존재하지 않아 null을 리턴
		isLogon = (Boolean) session.getAttribute("isLogon");
		if (isLogon != null) {
			// 로그인을 했을 경우, 세션으로부터 id와 pwㅇ를 받아와 작업 수행
			id = (String) session.getAttribute("login.id");
			pwd = (String) session.getAttribute("login.pwd");
			out.print("<html><body>");
			out.println("아이디: " + id + "<br>");
			out.println("비밀번호: " + pwd + "<br>");
			out.println("</body></html>");

		} else {
			response.sendRedirect("login.html");
		}
	}
}
