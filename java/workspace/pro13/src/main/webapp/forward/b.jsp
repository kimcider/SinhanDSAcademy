<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 b.jsp</h1>
	
	<!-- 
	test1
	여기서 이게 써지는 이유는, 
	a.jsp라는 하나의 요청이고 이 요청을 b.jsp와 공유하기 때문에 
	여기서 a.jsp에서 사용하는 name변수를 사용할 수 있음. -->
	
	<!-- 
	test2
	sendRedirect를 하면 새로운 요청이 되기 때문에 name변수를 사용할 수 없다. -->
		<%=request.getAttribute("name") %>
</body>
</html>