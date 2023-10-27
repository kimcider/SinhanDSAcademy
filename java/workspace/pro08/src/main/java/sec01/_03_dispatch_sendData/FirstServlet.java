package sec01._03_dispatch_sendData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/first5")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//리퀘스트를 이용해서 dispatch에서 값을 전달.
		request.setAttribute("name", "hong");	
		RequestDispatcher rd = request.getRequestDispatcher("second5");
		rd.forward(request, response); 	//이렇게 forward로 request를 넘겨주지 않고
//		response.sendRedirect("second5");	//redirect로 넘겨주면 request객체를 넘겨줄 수 없기에 데이터를 전송할 수 없다.
	}
}
