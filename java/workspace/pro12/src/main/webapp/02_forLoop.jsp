<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	java.util.List list = new java.util.ArrayList();
	list.add("홍길동");
	list.add("김길동");
	list.add("최길동");
%>
<html>
<body>
	<table border="1">
		<tr>
			<th>이름</th>
		</tr>
		
		<!-- 여기서부터 이제 이름들을 for문으로 출력시키고싶다. ㅎㅎ -->
		<!-- 이런식으로 for문과 html코드를 적절하게 섞어가면서 사용할 수 있다.  -->
		<% for(int i = 0; i < list.size(); i++){ %>
			<!-- 이런식으로 클래스도 막 달라지게 쓸 수 있다. -->
			<!-- 그런데 이렇게 쓸때는 if문으로 여러줄을 쓰면 또 안먹기때문에 이럴떄는 삼항연산자를 써야한다. -->
			<tr class="<%=i%2==0?"a":"b" %>">  
				<td><%=list.get(i) %></td>
			</tr>
		<%} %>
	</table>
</body>
</html>