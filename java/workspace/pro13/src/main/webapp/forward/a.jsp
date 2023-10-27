<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setAttribute("name", "홍길동");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 이거 왜 실행하면 a.jsp는 안나오냐
		forward로 b.jsp로 넘겨주니까 실행화면에는 a.jsp는 나오지 않고, b.jsp 파일이 응답한다.
 -->
	<h1>여기는 a.jsp</h1>
	<%System.out.println("1"); %>
	<!-- test1 -->
	<jsp:forward page="b.jsp"/>
	<%System.out.println("2"); //forward를 하면 이제 여기는 실행되지 않는다%>
	
	
	<!-- test2 -->
	<%//response.sendRedirect("b.jsp"); %>
</body>
</html>