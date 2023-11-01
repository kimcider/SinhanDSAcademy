<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="file1" value="${param.param1 }"/>
<c:set var="file2" value="${param.param2 }"/>
</head>
<body>
	매개변수 1:
	<c:out value="${file1 }"/><br>
	매개변수 2:
	<c:out value="${file2 }"/><br>
	
	<c:if test="${not empty file1}">
		<!-- <img src="${contextPath }/download.do?fileName=${file1}" width=300 height=300/><br> -->
		<!-- 이런식으로 쓰면 파일 명을 감출 수 있다. -->
		<img src="${contextPath }/download.do?fileName=${file1}" width=300 height=300/><br>
		<!-- 아래처럼 하면 파일명, 경로명이 노출된다.  -->
		<!-- <img src="실제 경로명, 파일명" width=300 height=300/><br> -->
	</c:if><br>
	<c:if test="${not empty file2 }">
		<img src="${contextPath }/download.do?fileName=${file2}" width=300 height=300/><br>
	</c:if><br>
	<a href="${contextPath}/download.do?fileName=${file1}">
	파일 1 내려받기</a><br>
	<a href="${contextPath}/download.do?fileName=${file2}">
	파일 2 내려받기</a><br>
</body>
</html>