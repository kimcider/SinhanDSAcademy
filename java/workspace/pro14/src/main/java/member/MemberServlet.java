package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//검색어 파라미터 받아야함. 
		String searchWord = request.getParameter("name");
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setName(searchWord);
		List list = dao.listMembers(vo);
		
		request.setAttribute("hi", "hi");
		//데이터 저장
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/member/member.jsp");
		rd.forward(request, response);
	}
}