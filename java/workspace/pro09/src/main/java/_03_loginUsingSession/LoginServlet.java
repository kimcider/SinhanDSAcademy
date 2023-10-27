package _03_loginUsingSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 클라이언트로부터 입력된 id/pw를 기반으로 VO생성
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pw);
		System.out.println(memberVO);
		
		// VO를 기반으로 해당 유저가 존재하는지 확인
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		
		// 유저가 존재할 경우 세션을 생성하고 세션에 유저 정보를 저장
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pwd", user_pw);
			out.println("<html><body>");
			out.println("안녕하세요 " + user_id + "님!!<br>");
			out.println("<a href='show'>회원정보 보기</a>");
			out.println("</body></html>");
		}
		// 유저가 존재하지 않을 경우 
		else {
			out.println("<html><body><center>회원 아이디가 틀립니다.");
			out.println("<a href='login.html'> 다시 로그인하기</a>");
			out.println("</body></html>");
		}
	}
}
