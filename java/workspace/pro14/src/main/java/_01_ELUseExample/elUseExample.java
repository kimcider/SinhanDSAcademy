package _01_ELUseExample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/elExample")
public class elUseExample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("name", "홍길동");
		HttpSession session = request.getSession();
		session.setAttribute("email", "hong@gmail.com");
		session.setAttribute("name", "세션길동");
		
		RequestDispatcher rd = request.getRequestDispatcher("/el/02_useExample.jsp");
		rd.forward(request, response);
	}
}
