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
@WebServlet("/first/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//프로젝트명
		out.println("컨텍스트 패스: " + request.getContextPath() + "<br>");
		//전체경로
		out.println("URL: " + request.getRequestURL().toString() + "<br>");
		//localhost:8000/뒤에있는거
		out.println("URI: " + request.getRequestURI() + "<br>");
		//get방식으로 값이 전달된 뒤에 있는 전부 다. 
		//localhost:8000/pro10/first/test?name=3 이런식으로 하면 null안나옴
		out.println("QueryString: " + request.getQueryString() + "<br>");
		//실제로 실행되는곳
		out.println("실제 경로: " + request.getRealPath("/") + "<br>");
		out.println("아이피: " + request.getRemoteAddr() + "<br>");
		
			
	}
}
