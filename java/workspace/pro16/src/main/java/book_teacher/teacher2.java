package book_teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/teacher2")
public class teacher2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();

		String title = request.getParameter("searchWord");
		String res = useAPI(title);
		
		// 메타데이터들을 저장하기 위해 map객체를 사용! 
		Map<String, Object> map = new HashMap<>();
		
		try {
			JSONParser JsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)JsonParser.parse(res);
			
			// 응답받은 JSON객체에서 JSON배열을 추출
			// 네이버 api에서 결과값들을 items배열에 넣어준다.
			JSONArray items = (JSONArray)jsonObject.get("items");
			
			// 배열 안의 JSON객체를 정제해서 보관할 list 생성
			List<Map<String, String>> list = new ArrayList<>();
			
			// 배열 안의 JSON객체를 정제, list에 보관
			for(int i = 0; i < items.size(); i++) {
				JSONObject item = (JSONObject)items.get(i);
				HashMap<String, String> object = new HashMap<>();
				object.put("image", (String)item.get("image"));
				object.put("title", (String)item.get("title"));
				object.put("author", (String)item.get("author"));
				object.put("publisher", (String)item.get("publisher"));
				object.put("link", (String)item.get("link"));
				
				list.add(object);
			}
			
			// list를 map에 저장
			map.put("list", list);
			// map을 사용했기에, 전체 응답의 갯수 등을 편하게 저장할 수 있다.
			map.put("total", jsonObject.get("total")); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		// request 객체에 map을 저장 후, 포워드로 데이터를 넘겨준다.
		request.setAttribute("map", map);
		RequestDispatcher rd = request.getRequestDispatcher("/teacher/bookList.jsp");
		rd.forward(request, response);
	}

	
	
	private String useAPI(String title) {
		String clientId = "M5BgvZasaga2SPnoBs_O"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "PLaqsQNNGP"; // 애플리케이션 클라이언트 시크릿

		String text = null;
		try {
			text = URLEncoder.encode(title, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text; // JSON 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

//		System.out.println(responseBody);
		return responseBody;
	}

	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
	}

}
