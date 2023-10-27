<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 절대경로로 쓰려면 서버에서는 프로젝트명경로가 빠지지만, 클라이언트에서는 이게 무조건 들어가야한다. -->
	<form action="/pro05/second" method="post">
		<input type="submit" value="전송">
	</form>
</body>
</html>