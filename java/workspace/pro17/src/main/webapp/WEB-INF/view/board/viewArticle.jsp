<!-- c:set var =contextPath 이거 없애보기.  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>글 상세</title>
<script>
	function modify(){
		location.href="modArticleForm.do?articleno=${article.articleno}";
	}
</script>
</head>
<body>
	<h1 style="test-align: center">글 상세</h1>
	<table align="center">
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">글번호</td>
			<td>${article.articleno }</td>
		</tr>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">아이디</td>
			<td>${article.id }</td>
		</tr>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">글제목</td>
			<td>${article.title }</td>
		</tr>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">글내용</td>
			<!--  이런식으로 엔터를 br태그로 바꿀 수 있따. 여기서의 getContentToHtml은 필드가 아니라 메소드-->
			<td>${article.contentToHtml}</td>
		</tr>

		<c:if test="${not empty article.imagefilename}">
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">이미지</td>
				<td><img
					src="/pro17/download.do?path=/article_image&fileName=${article.imagefilename }"></td>
			</tr>
		</c:if>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
			<td>${article.writedate }</td>
		</tr>
		<tr>
			<td colspan='2' align="center">
				<input type="button" value="수정하기" onclick="modify();">
				<input type="button" value="삭제하기">
				<input type="button" value="목록보기" onclick="location.href='listArticles.do';">
				<input type="button" value="답글쓰기">
			</td>
		</tr>
	</table>
</body>
</html>