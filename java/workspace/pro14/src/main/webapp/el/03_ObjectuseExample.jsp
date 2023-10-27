<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- MemberVO객체의 name필드를 getName()함수로 가져옴
		그래서 getName()메소드가 없으면 이게 동작을 안함!!
	 -->
	<!-- //MemverVO 객체를 request에 저장한거를 가져다쓰기 -->
	${member.name }
	
	<!-- //MemberVo 객체를 map객체에 저장한거를 request객체에 저장 -->
	<br>
	${map.vo.name }
	${requestScope.map.vo.name }<!-- rquest객체 안에 있는거니까 이코드나 위코드나 똑같다. -->
</body>
</html>