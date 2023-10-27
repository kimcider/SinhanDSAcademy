package sec01._03_dispatch_sendData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/second5")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//이렇게 리퀘스트 객체에 담겨온 내용을 가져다쓰면된다.
		String name = (String)request.getAttribute("name");	//우리가 보낸게 String이기때문에 (String)으로 다시 바꿔줘야한다.
		out.print("<html><body>");
		out.print(name);
		out.print("</html></body>");
	}
}
