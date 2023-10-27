package sec01.ex01_connectToDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member1")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		MemberDAO_03_DataSource dao = new MemberDAO_03_DataSource();
		List<MemberVO> list = dao.listMembers();
		printToClient(pw, list);
	}
	
	private void printToClient(PrintWriter pw, List<MemberVO> list) {
		pw.println("<html>");
		pw.println("<style>");
		pw.println("table, tr, td{border:1px solid black}");
		pw.println("</style>");
		pw.println("<body>");
		pw.println("<table>");
		
		pw.println("<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");
		for(int i = 0; i < list.size(); i++) {
			MemberVO memberVO = list.get(i);
			pw.println("<tr>" + "<td>"+ memberVO.getId() +"</td>" + "<td>"+ memberVO.getPwd() +"</td>" + "<td>"+ memberVO.getName() +"</td>" + "<td>"+ memberVO.getEmail() +"</td>" +"<td>"+ memberVO.getJoinDate() +"</td>" +  "</tr>");
		}
		
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
	}
}