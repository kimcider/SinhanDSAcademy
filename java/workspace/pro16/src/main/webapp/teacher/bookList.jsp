<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	총개수 : ${map.total }<br>
	<table border="1">
		<tr>
			<td>이미지</td>
			<td>제목</td>
			<td>저자</td>
			<td>출판사</td>
		</tr>
		<c:forEach var='item' items='${map.list }'>
			<tr>
				<td><a href="${item.link }" target="_blank"><img src="${item.image }" width=100></a></td>
				<td>${item.title }</td>
				<td>${item.author }</td>
				<td>${item.publisher }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>