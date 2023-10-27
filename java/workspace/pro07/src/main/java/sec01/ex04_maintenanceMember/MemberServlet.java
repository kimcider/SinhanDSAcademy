package sec01.ex04_maintenanceMember;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member4")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		MemberDAO dao = new MemberDAO();
		
		String command = request.getParameter("command");
		if("addMember".equals(command)) {
			MemberVO vo = new MemberVO();
			vo.setId(request.getParameter("id"));
			vo.setPwd(request.getParameter("pwd"));
			vo.setName(request.getParameter("name"));
			vo.setEmail(request.getParameter("email"));
			dao.addMember(vo);
		} else if("removeMember".equals(command)) {
			String targetID = request.getParameter("id");
			String targetPwd = request.getParameter("pwd");
			dao.removeMember(targetID, targetPwd);
		}else if("updateMember".equals(command)) {
			MemberVO vo = new MemberVO();
			vo.setId(request.getParameter("id"));
			vo.setPwd(request.getParameter("pwd"));
			vo.setName(request.getParameter("name"));
			vo.setEmail(request.getParameter("email"));
			dao.updateMember(vo);
		}
		
		
		List<MemberVO> list = dao.listMembers();
		printTable(pw, list);
		
	}
	private void printTable(PrintWriter pw, List<MemberVO> list) {
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
		
		pw.println("<button><a href='registeForm.html'>회원 가입으로 이동</a></button>");
		pw.println("<t>");
		pw.println("<button><a href='updateForm.html'>회원 수정으로 이동</a></button>");
		pw.println("<t>");
		pw.println("<button><a href='removeForm.html'>회원 삭제로 이동</a></button>");
		
		pw.println("</body>");
		pw.println("</html>");
	}
}