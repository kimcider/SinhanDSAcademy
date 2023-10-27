<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 아래 속성이 true이면 소스에 공백이 trim된다. -->
<!-- 몇몇 api는 공백이 있으면 에러가 나는 경우가 있으니 알아만 두라 -->
<%@ page trimDirectiveWhitespaces="true" %>

<!-- import class -->
<%@ page import="test.Test" %>
<%@ page import="java.util.Date" %>
<html>
<body>
	<%  //이 안에 자바코드를 작성
		Test t = new Test();
		String str = t.tempMethod();
		
		//출력 방법 1. 내장객체 'out'을 사용
		out.print(str);
	%>
	<br>
	<!-- 출력방법 2. 표현식을 사용 표현식에는 ;를 사용하지 않음 -->
	<%= new Date() %>
</body>
</html>