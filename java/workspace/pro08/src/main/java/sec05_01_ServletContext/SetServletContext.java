package sec05_01_ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetServletContext
 */
@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//프로젝트 전체에서 공유할 수 있는게 servletContext
		//servletContext객체를 받아오는것
		ServletContext ctx = getServletContext();
		List member = new ArrayList();
		member.add("이순신");
		member.add(30);
		ctx.setAttribute("member", member);
		pw.print("이순신과 30 설정");
	}
}
