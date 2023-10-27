package _02_Session;

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

@WebServlet("/set3")
public class SetCookieValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		// session 객체 생성
		HttpSession sess = request.getSession();
		
		// session이라는 저장소에 정보를 저장
		// 이렇게 만들어진 세션은 브라우저 전체에서 쓸 수 있다.
		sess.setAttribute("name", "세션길동");
		
		Map map = new HashMap();
		map.put("id", "hong");
		map.put("name", "홍길동");
		map.put("email", "hong@gmail.com");
		
		//세션이라는 저장소를 통해 원하는 형태의 데이터를 저장할 수 있다.
		sess.setAttribute("loginInfo", map);
	}
}