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
총 개수 수 : ${map.count }<br>
<!-- 사실 여기서 param.page는 ArticleVO의 page로 넘어가기 때문에 
	컨트롤러에서 ArticleVO를 request에 넣어주고
	vo.page를 쓰면된다.
 -->
<!-- 총 페이지 수 : ${param.page}/${map.totalPage }<br> -->
총 페이지 수 : ${vo.page}/${map.totalPage }<br>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td><b> 글번호 <b></td>
			<td><b> 작성자 <b></td>
			<td><b> 제목 <b></td>
			<td><b> 작성일 <b></td>
		</tr>
	
		<c:forEach var="article" items="${map.articlesList }" varStatus="articleNum">
			<tr align="center">
				<!-- <td width="5%">${articleNum.count }</td> -->
				<!-- 이거 글번호를 pk가 아니라 그냥 사용자에게 표시되는 글의 갯수로만 표시하게 하는법 -->
				<td width="5%">${map.count - articleNum.index - (vo.page - 1) * vo.rowPage }</td>
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
		<!-- 페이지 번호 -->
		<tr align="center">
			<td colspan="4">
				<c:if test="${map.prev }">
					<a href="listArticles.do?page=${map.startPage-1 }">[이전]</a>
				</c:if>
				<!-- <//c//:forEach var="num" begin="1" end="${map.totalPage}"> -->
				<c:forEach var="num" begin="${map.startPage }" end="${map.endPage }">
					<!-- 가끔 이거를 자바스크립트로 post방식으로넘겨서 주소창에 안띄우는 경우도 있긴 하다. 그런데 그렇게하면 친구한테 링크주는게 안됨 ㅎㅎ... -->
					<a href="listArticles.do?page=${num }&searchWord=${vo.searchWord}">[${num }]</a>
				</c:forEach>
				<c:if test="${map.prev }">
					<a href="listArticles.do?page=${map.startPage+1 }">[다음]</a>
				</c:if>
			</td>
		</tr>
		<!-- 검색창 -->
		<tr align="center">
			<td colspan="4">
				<form>
									<!-- 이때 value를 안쓰면 ㅎㅎ... -->
					<input type="text" name="searchWord" value="${vo.searchWord }">
					<input type="submit" value="검색">
				</form>
			</td>
		</tr>
	</table>
	<a href="articleForm.do" class='cls1'>
		<p class='cls2'>글쓰기</p>
	</a>
</body>
</html>