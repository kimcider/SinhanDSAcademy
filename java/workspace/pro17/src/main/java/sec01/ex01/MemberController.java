package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이런식으로 맴버와 관련된 처리들을 다 이 서블릿으로 불러올 수 있다.
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;

		/*
		 * http://localhost:8000/pro17/member/asdf 이런식으로 접속하면 member/뒤의 asdf가 action으로
		 * 들어온다. 이렇게 들어온 경로가 뭐냐에 따라서 다르게 동작하도록 할것
		 */
		String action = request.getPathInfo();
		System.out.println("action: " + action);

		if (action == null || "/listMembers.do".equals(action)) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test01/listMembers.jsp";
		} else if ("/addMember.do".equals(action)) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(vo);
			// 포멧이 리다이렉트를 하지 않고, 문자열을 리턴을하게되어있을경우
			// 등록을 하면 forward를 하면 안된다.
			// 등록할떄 forward를 하면 새로고침을 하면 계속 등록이된다.
			// 결제도 마찬가지
			// 이런 개념 매우 중요!!!!!!!!
			nextPage = "redirect:listMembers.do";
		} else if ("/memberForm.do".equals(action)) {
			nextPage = "/test01/memberForm.jsp";
		} else if ("/modMember.do".equals(action)) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(vo);
			nextPage = "redirect:listMembers.do";
		} else if ("/modMemberForm.do".equals(action)) {
			String id = request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);
			request.setAttribute("memInfo", memInfo);
			nextPage = "/test01/modMemberForm.jsp";
		} else if ("/delMember.do".equals(action)) {
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			nextPage = "redirect:listMembers.do";
		}else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test01/listMembers.jsp";
		}

		if (nextPage.startsWith("redirect:")) {
			// redirect가 앞에 붙어있으면 redirect를 수행
			response.sendRedirect(nextPage.replace("redirect:", ""));
		} else {
			// 포워딩될 경로를 변수로 설정하기!!
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}

	}
}
