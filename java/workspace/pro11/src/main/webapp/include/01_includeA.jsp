<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
//여기다 선언하고 b에서 사용하면?
		/*
			b.jsp파일로 가봐라
			b.jsp 파일에서는 에러가 나지만 실제로 출력을 해 보면 출력이 잘 된다.
			b.jsp의 소스를 그대로 가져다가 a.jsp의 그 부분에 넣는다고 생각하면 된다.
		*/
	String name = "홍길동";
	
%>    
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 a.jsp</h1>



	<!-- 이렇게 a.jsp 사이에 b.jsp를 불러올 수 있다. 
		디렉티브 방식  -->
	<!-- 
	반드시 외워야 할 주의사항!!!!
		주의할점은 이 코드는 서버에서 실행되는것이다. 
		얘는 서버에서 동작하는 코드이기 때문에 /pro11/이렇게 쓰면 안된다!
		클라이언트에서 경로를 쓸 때는 contextPath를 써야만한다.
	 -->
	<%@ include file="/include/01_includeB.jsp"%>
	
	
	
	<h1>여기는 a.jsp</h1>
</body>
</html>