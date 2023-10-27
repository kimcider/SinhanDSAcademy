<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>여기는 b.jsp</h1>

<!-- 이 파일에서는 에러가 나지만 실제로 출력을 해 보면 출력이 잘 된다.
	b.jsp의 소스를 그대로 가져다가 a.jsp의 그 부분에 넣는다고 생각하면 된다.
 -->
<%=name %>

<!-- 이런식으로 리퀘스트객체로 받아쓸 수 있다. -->
<%=request.getParameter("email")%>