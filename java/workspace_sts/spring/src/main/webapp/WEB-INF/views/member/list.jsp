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
	<table border="1">
		<tr>
			<td>no</td>
			<td>id</td>
			<td>name</td>
			<td>filename_org</td>
			<td>download</td>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.no } </td>
				<td>${vo.id } </td>
				<td>${vo.name } </td>
				<td>${vo.filename_org } </td>
					<td>
						<form action="download.do">
							<input type="hidden" name="filename_org" value="${vo.filename_org }">
							<input type="hidden" name="filename_real" value="${vo.filename_real }">
							<input type="submit" value="다운로드">
						</form>
					</td>
			</tr>
		</c:forEach>
		
	</table>
	
	<c:if test="${!empty loginSess }">
		<input type="button" value="등록" onclick="location.href='regist.do';">
	</c:if>
</body>
</html>