<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${name }<br>
	${email }<br>
	
	${sessionScope.name }<br>
	
	<!-- 이렇게 없는 속성명을 써도 null이나 에러가 뜨지 않는다!! -->
	${congcong }<br>
</body>
</html>