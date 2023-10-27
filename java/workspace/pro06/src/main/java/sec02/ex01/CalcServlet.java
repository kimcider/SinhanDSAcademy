package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final float USD_RATE = 1100.50f;
	private static final float JPY_RATE = 9.02f;
	private static final float CNY_RATE = 184f;
	private static final float GBP_RATE = 1800f;
	private static final float EUR_RATE = 1353.50f;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송되는 파라미터 한글 처리 
		request.setCharacterEncoding("utf-8");
		
		// 응답(response) 관련
		response.setContentType("text/html; charset=utf-8"); //MIME-TYPE, charset 설정
		PrintWriter pw = response.getWriter();	//PrintWriter 객체 생성
		
		// 클라이언트로부터 전송되는 값
		String command = request.getParameter("command");	
		String won = request.getParameter("won");
		String operator = request.getParameter("operator");
		
		//변환 버튼을 클릭했다는것. 
		//변환 버튼을 클릭하지 않고, 맨 처음 /pro06/calc로 접속하면 command가 null이기 때문에 여기를 실행하지 않는다.
		//이렇게 하지 않고, 다음과같이 작성해도 가능하다.
		//if("calculate".equlas(command)) 이런식으로 해도 가능하다.
		if(command != null && "calculate".equals(command)) {
			String result = calculate(Float.parseFloat(won), operator);
			pw.print("<html><font size=10>변환결과</font><br>");
			pw.print("<html><font size=10>" + result + "</font><br>");
			pw.print("<a href='/pro06/calc'>환율 계산기</a>");
			return;
		}
		
		pw.print("<html><title>환율 계산기</title>");
		pw.print("<font size=5>환율 계산기</font><br>");
		pw.print("<form name='frmCalc' method='get' action='/pro06/calc' />");
		pw.print("원화: <input type='text' name='won' size=10 />");
		pw.print("<select name='operator' >");
		pw.print("<option value='dollar'>달러</option>");
		pw.print("<option value='en'>엔화</option>");
		pw.print("<option value='wian'>위안</option>");
		pw.print("<option value='pound'>파운드</option>");
		pw.print("<option value='euro'>유로</option>");
		pw.print("</select>");
		
		//제출할 때, command요소의 value가 calculate임을 변하지 못하게하도록 이 요소를 hidden으로 설정해놨다. 
		pw.print("<input type='hidden' name='command' value='calculate' />");
		
		
		pw.println("<input type='submit' value='변환' />");
		pw.println("</form>");
		pw.println("</html");
		pw.close();
	}

	private static String calculate(float won, String operator) {
		String result = null;
		
		if(operator.equals("dollar")) {
			result = String.format("%.6f",  won / USD_RATE);
		}else if(operator.equals("en")) {
			result = String.format("%.6f",  won / JPY_RATE);			
		}else if(operator.equals("wian")) {
			result = String.format("%.6f",  won / CNY_RATE);			
		}else if(operator.equals("pound")) {
			result = String.format("%.6f",  won / GBP_RATE);			
		}else if(operator.equals("euro")) {
			result = String.format("%.6f",  won / EUR_RATE);			
		}
		
		return result;
	}
}
