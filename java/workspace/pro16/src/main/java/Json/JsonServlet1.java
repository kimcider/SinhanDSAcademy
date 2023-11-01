package Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/json")
public class JsonServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 문자열로 전송된 JSON 데이터를 request객체를 사용해 가져온다.
		String jsonInfo = request.getParameter("jsonInfo");
		try {
			// 전송된 JSON데이터를 처리하기 위한 JSONParser 객체를 생성
			JSONParser jsonParser = new JSONParser();
			// 전송된 JSON데이터를 파싱
			JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonInfo);
			System.out.println("* 회원 정보 *");
			
			//JSON객체의 속성명으로 값에 접근
			System.out.println(jsonObject.get("name"));
			System.out.println(jsonObject.get("age"));
			System.out.println(jsonObject.get("gender"));
			System.out.println(jsonObject.get("nickname"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
