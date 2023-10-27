package sec05_02_;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		ServletContext ctx = getServletContext();
		String menu_member = ctx.getInitParameter("menu_member");
		String menu_order = ctx.getInitParameter("menu_order");
		String menu_goods = ctx.getInitParameter("menu_goods");
		
		pw.print("<html><body>");
		pw.println("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
		pw.println("<tr><td>" + menu_member + "</td></tr>");
		pw.println("<tr><td>" + menu_order + "</td></tr>");
		pw.println("<tr><td>" + menu_goods + "</td></tr>");
		pw.print("</table></body></html>");
	}
}
