package testExample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radiogugudan")
public class GugudanRadioBoxVersion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String number = request.getParameter("number");
		
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<style> table{text-align: center;} </style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<table border='1px solid black'>");
		pw.println("<tr><td colspan=3>" + number + "단 출력</td></tr>");
		for(int i = 1; i <= 9; i++) {
			if(i % 2 == 0) pw.println(
					"<tr>"
					+ "<td width='150px' style='background-color:#ACFA58;'>" + "<input type='radio' name='number' value='" + i + "'>" + i + "</td>"
					+ "<td width='300px' style='background-color:#ACFA58;'>" + number + " * " + i + "</td>"
					+ "<td width='300px' style='background-color:#ACFA58;'>" + (Integer.valueOf(number) * i) + "</td>"
				+ "</tr>");
			if(i % 2 == 1) pw.println(
					"<tr>"
					+ "<td width='150px' style='background-color:#81BEF7;'>" + "<input type='radio' name='number' value='" + i + "'>" + i + "</td>"
					+ "<td width='300px' style='background-color:#81BEF7;'>" + number + " * " + i + "</td>"
					+ "<td width='300px' style='background-color:#81BEF7;'>" + (Integer.valueOf(number) * i) + "</td>"
				+ "</tr>");
		}
		pw.println("</table>");
		
		pw.println("<script>");
		pw.println("let newAttr = document.createAttribute('checked');");
		pw.println("let radioArray = document.querySelectorAll('input');");
		pw.println("for(let i = 0; i < radioArray.length; i++){ "
				+ " if(radioArray[i].value == " + number + "){" 
						+ "console.log(5); radioArray[i].setAttributeNode(newAttr);"
						+ "}"
				+ "}");
		pw.println("</script>");
		
		pw.println("</body>");
		pw.println("</html>");
		
	}
}
