package _01_cookie._02_SessionCookie;

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

@WebServlet("/get2")
public class GetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		Cookie[] allValues = request.getCookies();
		for(int i = 0; i < allValues.length; i++) {
			if(allValues[i].getName().equals("sessionCookies")) {
				pw.println("<h2>SessionCookie값 가져오기: " + URLDecoder.decode(allValues[i].getValue(), "utf-8"));
			}
		}
	}
}
