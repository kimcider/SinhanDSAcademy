<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	int number = Integer.valueOf(request.getParameter("number"));
%>
<html>
<head>
	<style>
		.a{
			background-color: #CCFF66;
		}
		.b{
			background-color: #CCCCFF;
		}
		
		td{
			width: 200;
			text-align: center;
		}
	</style>
</head>
<body>
	<table border=1>
		<tr>
			<td colspan=2><%=number %>단 출력</td>
		</tr>
		
		<%for(int i = 1; i <= 9; i++){ %>
			<tr class=<%= i % 2 == 0 ? "a" : "b" %> >
				<td><%=number %> * <%=i %></td>
				<td><%=number * i %></td>
			</tr>
		<%} %>
	</table>
</body>
</html>