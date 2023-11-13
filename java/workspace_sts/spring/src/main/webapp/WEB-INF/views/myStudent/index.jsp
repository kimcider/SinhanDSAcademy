<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<h1>회원 목록</h1>
	<form method="get">
		<input type="text" name="searchWord" value="${param.searchWord }">
		<input type="submit" value="검색">
	</form>
	
	<c:forEach var="vo" items="${list }">
		${vo.studno } ${vo.id } ${vo.name }<br>
	</c:forEach>
</body>
</html>