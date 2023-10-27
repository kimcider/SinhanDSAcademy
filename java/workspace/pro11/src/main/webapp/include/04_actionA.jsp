<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
	<!-- 액션태그는 이런식으로 변수를 보낼 수 있다.
		parameter로 변수를 보내면 actionB.jsp에서 request객체에서 그것을 받아서 사용한다. -->
	<h1>여기는 testA.jsp</h1>
	<jsp:include page="04_actionB.jsp">
		<jsp:param name="email" value="rlaqucjs96@naver.com"/> 
	</jsp:include>
	<h1>여기는 testA.jsp</h1>
</body>
</html>