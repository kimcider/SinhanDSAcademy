package _01_cookie._02_SessionCookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/set2")
public class SetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		Cookie c = new Cookie("sessionCookies", URLEncoder.encode("세션쿠키!!", "utf-8"));
		c.setMaxAge(-1);//시간을 -1로 주면 세션쿠키로 만들어진다!!!
		response.addCookie(c);
		
		Date d = new Date();
		pw.println("현재시간: " + d);
		pw.println("문자열을 Cookie에 저장합니다.");
	}
}