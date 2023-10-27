package _02_Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetCookieValue
 */
@WebServlet("/get3")
public class GetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		// session 객체 생성
		HttpSession sess = request.getSession();
		String name = (String)sess.getAttribute("name");	//session의 name속성의 값 가져오기
		pw.println("<h2>session의 name속성의 값 가져오기!!</h2>");
		pw.println("<h4>" + name + "</h4>");
		pw.println("<br><br>");
		
		Map m = (Map)sess.getAttribute("loginInfo");	//session의 loginInfo속성의 값 가져오기
		pw.println("<h2>session의 loginInfo속성의 값 가져오기!!</h2>");
		pw.println("<h4>" + m + "</h4>");
		pw.println("<h4>id: " + m.get("id") + "</h4>");
		pw.println("<h4>name: " + m.get("name") + "</h4>");
		pw.println("<h4>email: " + m.get("email") + "</h4>");
	}
}
