package sec05_01_ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
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
//이거 cset을 먼저 실행시키고 이거를 실행해야 결과가 뜬다.
//cset을 수행하지 않으면 servletcontext에서 값들이 설정되어있지 않았기 때문에 이 값들을 받아올 수 없는듯하다. 
@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		ServletContext ctx = getServletContext();
		List member = (List)ctx.getAttribute("member");
		pw.print(member.get(0));
		pw.print(member.get(1));
	}
}