<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = "홍길동";
%>    
<html>
<body>
	
	<!-- 액션태그는 이렇게 호출을하고 actionErrorB에 가보면 거기에는 name이 선언되어있지 않다.
	directive방식은 코드 자체를 가져오는거라서 actionErrorB에 name변수가 선언되어있지 않아도
	여기에 선언되어있어서 사용이 가능하다.
	
	그러나, action태그는 거기서 실행을 한 후, 그 결과를 가져오는 것이기 때문에 거기에 name변수가 없으면 안된다. -->
	<jsp:include page="actionErrorB.jsp"/>
</body>
</html>