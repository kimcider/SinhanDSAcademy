package _01_cookie._01_base;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/set1")
public class SetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		Date d = new Date();
		//쿠키 객체 생성
		//쿠키를 생성할 때 한글이 있으면 깨질 수 있음으로 URLEncoder를 통해 쿠키 정보를 생성해야 한다.
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", "utf-8"));
		//쿠키 만료 시간 설정
		c.setMaxAge(24 * 60 * 60);
		//응답에 쿠키 정보 전송
		response.addCookie(c);
		
		pw.println("현재시간: " + d);
		pw.println("문자열을 Cookie에 저장합니다.");
	}
}
