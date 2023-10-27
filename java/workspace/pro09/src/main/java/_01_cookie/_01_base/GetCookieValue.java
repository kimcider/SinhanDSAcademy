package _01_cookie._01_base;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get1")
public class GetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//브라우저에 쿠키 정보를 요청한 후, 쿠키 정보를 배열로 가져온다.
		Cookie[] allValues = request.getCookies();
		
		for(int i = 0; i < allValues.length; i++) {
			if(allValues[i].getName().equals("cookieTest")) {
				pw.println("<h2>Cookie값 가져오기: " + URLDecoder.decode(allValues[i].getValue(), "utf-8"));
			}
		}
	}
}
