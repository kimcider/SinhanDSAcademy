package util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import chapter11.MemberVO;

public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession sess = request.getSession();
		MemberVO login = (MemberVO)sess.getAttribute("loginSess");
		
		if(login == null) {
			//그냥 보내긴 좀 뭐하니까 얼럿을 띄어주자
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('로그인 후 사용 가능합니다.');");
			//그리고 로그인하는곳으로 보내준다.
			//여기서 test는 contextPath이다. 
			//다른이름으로 지정했으면 이 test를 바꿔야한다. 
			out.print("location.href='/test/member/login.do';");
			out.print("</script>");
			out.close();
			return false; //세션이 없으면 
		}
		return true;
	}

}
