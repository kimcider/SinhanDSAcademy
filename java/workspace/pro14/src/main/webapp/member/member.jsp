<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="member.do">
		<!-- param.name을 하면, 내가 검색했던 값을 여기 다시 뜨게 할 수 있다. 
			이 페이지에서 member.do로 전송할 때 name/value를 request에 parameter로 넘기기 때문에
		 -->
		이름: <input type="text" name="name" value="${param.name }">
		<input type="submit" value="조회하기">
	</form>
	<h1>회원 정보 출력</h1>

	<table border=1 width=800 align=center>
		<tr align=center bgcolor='#FFFF66'>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
		
		<c:forEach var="member" items="${list }">
			<tr>
				<td>${member.id }</td>
				<td>${member.pwd }</td>
				<td>${member.name }</td>
				<td>${member.email }</td>
				<td>${member.joinDate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>