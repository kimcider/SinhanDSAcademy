package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/mem")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		String id = (String)request.getParameter("id");
		System.out.println("id = " + id);
		MemberDAO memberDAO = new MemberDAO();
		boolean overlappedID = memberDAO.overlappedID(id);
		
		// 맴버가 있냐 없냐에 따라서 아래와 같이 응답하게 하는것
		if(overlappedID == true) {
			writer.print("not_usable");
		}else {
			writer.print("usable");
		}
	}
}
