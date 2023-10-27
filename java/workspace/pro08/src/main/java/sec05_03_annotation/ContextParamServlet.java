package sec05_03_annotation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	여기서 설정하든 web.xml에서 설정하든 둘 중 하나에서만 설정해라 ㅎㅎ
	사실 주소 겹치는거 문제인데 괜히 두군데다 쓸 필요가 뭐가 있겠느냐.
	여기서 설정하려면 web.xml에서 선언해둔거 다 지워라.
*/
//@WebServlet(
//		urlPatterns = {
//				"/initMenu2",
//				"/initMenu3"
//		},
//		initParams = {
//				@WebInitParam(name = "email", value = "admin@jweb.com"),
//				@WebInitParam(name = "tel", value = "010-1111-2222")
//		},
//		loadOnStartup = 1)
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init (ServletConfig config) throws ServletException{
		/* 
		   	init을 재정의한 후, super.init(config)를 수행하지 않으면
		  	HttpServlet객체 내의 ServletConfig객체가 초기화되지 않아서 사용할 수 없게된다.
		 */
		super.init(config);
		
		System.out.println("init 메서드 호출");
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		System.out.println("email: " + email);
		System.out.println("tel: " + tel);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		ServletContext ctx = getServletContext();
		String menu_member = ctx.getInitParameter("menu_member");
		String menu_order = ctx.getInitParameter("menu_order");
		String menu_goods = ctx.getInitParameter("menu_goods");
		
		/*
			email는 web.xml의 context-parameter로 선언된 것이 아니다.
			survlet태그 안에 parameter로 선언되어있다.
			따라서, ServletContext에서 이름이 email인 parameter를 찾으려 하면
			null을 리턴하게된다.
		*/
		String email1 = ctx.getInitParameter("email");
		System.out.println("ctx에서 찾은 email값: " + email1);
		
		/*
			email는 servlet태그 내에 선언되어있기 때문에
			ServletConfig객체 안에 정보가 저장된다. 
			
			때문에, ServletContext객체가 아닌 ServletConfig객체로부터 parameter를 찾아야 한다.
			
			HttpServlet클래스에 ServletConfig객체를 활용한 getInitParameter()메소드가 정의되어 있는 것으로 보인다.
		*/
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		System.out.println("ServletConfig에서 찾은 email값: " + email);
		System.out.println("ServletConfig에서 찾은 tel값: " + tel);
		pw.print("<html><body>");
		pw.println("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
		pw.println("<tr><td>" + menu_member + "</td></tr>");
		pw.println("<tr><td>" + menu_order + "</td></tr>");
		pw.println("<tr><td>" + menu_goods + "</td></tr>");
		pw.print("</table></body></html>");
		
	}
}
