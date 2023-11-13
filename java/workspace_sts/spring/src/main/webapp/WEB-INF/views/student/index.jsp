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
	총 학생 수 : ${total }
	<form action="search2.do" method="get">
		<select name="sgrade">
			<option value="">전체</option>
			<option value="1" <c:if test="${param.sgrade=='1' }">selected</c:if>>1학년</option>
			<option value="2" <c:if test="${param.sgrade=='2' }">selected</c:if>>2학년</option>
			<option value="3" <c:if test="${param.sgrade=='3' }">selected</c:if>>3학년</option>
			<option value="4" <c:if test="${param.sgrade=='4' }">selected</c:if>>4학년</option>
		</select>
		
		<select name="searchType">
			<option value="all" >전체</option>
			<option value="name" <c:if test="${param.searchType=='name' }">selected</c:if>>이름</option>
			<option value="id" <c:if test="${param.searchType=='id' }">selected</c:if>>ID</option>
		</select>
		<input type="text" name="searchWord" value="${param.searchWord }">
		<input type="submit" value="검색">
	</form>
	
	<c:forEach var="vo" items="${list }">
		${vo.studno } ${vo.id } ${vo.name } ${vo.telephone }<br>
	</c:forEach>
</body>
</html>