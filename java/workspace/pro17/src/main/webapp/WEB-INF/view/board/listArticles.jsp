<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.cls1 {
	text-decoration:none;
}

.cls2 {
	font-size: 30px;
	text-align: center
}
</style>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td><b> 글번호 <b></td>
			<td><b> 작성자 <b></td>
			<td><b> 제목 <b></td>
			<td><b> 작성일 <b></td>
		</tr>
	
		<c:forEach var="article" items="${articlesList }" varStatus="articleNum">
			<tr align="center">
				<td width="5%">${articleNum.count }</td>
				<td width="10%">${article.id }</td>
				<td align="left" width="35%">
					<span style='padding-right:5px'></span>
					<!-- 이런식으로 nested level에 따라서 들여쓰기를 한다!! ㄷㄷ..... -->
					<c:forEach begin="1" end="${article.nested }">
						<span style='padding-right:20px'></span>
					</c:forEach>
					<a href="viewArticle.do?articleno=${article.articleno }" class='cls1'>${article.title }</a>
				</td>
				<td width="10%">${article.writedate }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="" class='cls1'>
		<p class='cls2'>글쓰기</p>
	</a>
</body>
</html>