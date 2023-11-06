<!-- c:set var =contextPath 이거 없애보기.  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj){
		obj.action = "listArticles.do";
		obj.submit();
	}
	</script>
<meta charset="UTF-8">
<title>글 수정 창</title>
</head>
<body>
	<h1 style="test-align:center">글 수정하기</h1>
	<form name="articleForm" method="post" action="modArticle.do?" enctype="multipart/form-data">
	<input type="hidden" name="articleno" value="${article.articleno}">
		<table border="0" align="center">
			<tr>
				<td align="right">글 제목: </td>
				<td colspan="2"><input type="text" size="67" maxlength="500" name="title" value="${article.title }"/></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글 내용: </td>
				<td colspan="2"><textarea name="content" row="10" cols="65" maxlength="4000">${article.content }</textarea></td>
			</tr>
			<tr>
				<td align="right">이미지 파일 첨부: </td>
				<td colspan="2"><input type="file" name="imagefilename" onchange="readURL(this);"/></td>
				<!-- download.do를 기억하면 이 경로는 쉽게 유추할 수 있다. ㅎㅎ -->
				<td><img id="preview" src="/pro17/download.do?path=/article_image&fileName=${article.imagefilename }" width=200 height=200/></td>
			</tr>
			<tr>
				<td align="right"> </td>
				<td colspan="2">
					<input type="submit" value="수정하기"/>
					<input type="button" value='목록 보기' onClick="backToList(this.form)"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>