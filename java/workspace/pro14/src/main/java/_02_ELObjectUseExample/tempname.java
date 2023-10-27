package _02_ELObjectUseExample;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tempname
 */
@WebServlet("/tempname")
public class tempname extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MemverVO 객체를 request에 저장
		MemberVO vo = new MemberVO();
		vo.setName("김길동");
		request.setAttribute("member", vo);
		
		//MemberVo 객체를 map객체에 저장한거를 request객체에 저장
		Map map = new HashMap();
		map.put("vo", vo);
		request.setAttribute("map", map);
		
		RequestDispatcher rd = request.getRequestDispatcher("/el/03_ObjectuseExample.jsp");
		rd.forward(request, response);
	}
}
