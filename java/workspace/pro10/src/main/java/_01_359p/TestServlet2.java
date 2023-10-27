package _01_359p;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */


//이렇게 first/*으로 하면 first/이다음에 어떤 경로를 넣던 서버에서 다 받아진다. 
//Sprint에서는 "*.do"로 쓴다. 이러면 do로 끝나는거를 모두 받겠다는 의미이다. 
@WebServlet("/first/*")
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("컨텍스트 패스: " + request.getContextPath() + "<br>");
		out.println("URL: " + request.getRequestURL().toString() + "<br>");
		out.println("URI: " + request.getRequestURI() + "<br>");
		out.println("QueryString: " + request.getQueryString() + "<br>");
		out.println("실제 경로: " + request.getRealPath("/") + "<br>");
		out.println("아이피: " + request.getRemoteAddr() + "<br>");
	}
}
