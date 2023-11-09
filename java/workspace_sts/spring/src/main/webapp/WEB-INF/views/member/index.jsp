<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	member index;<br>
	커맨드객체	${memberVO.id } / ${memberVO.name }<br>
		
	리퀘스트객체 : ${mvo.id } / ${mvo.name }<br>
	세션객체 : ${svo.id } / ${svo.name }<br>
	모델객체 : ${requestScope.modelVO.id } / ${requestScope.modelVO.name }<br>
	모델앤뷰객체 : ${requestScope.mavvo.id } / ${requestScope.mavvo.name }<br>
	
</body>
</html>