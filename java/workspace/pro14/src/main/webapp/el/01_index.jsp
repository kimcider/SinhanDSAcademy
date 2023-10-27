<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//EL을 사용하면 저장소에 들어있는 속성명을 자동으로 찾아서 출력할 수 있다.
	request.setAttribute("name", "홍길동");
	session.setAttribute("email", "hong@gmail.com");
	
	//이런식으로 세션의 네임과 리퀘의 네임이 중복될수도있다.
	//앵간해서는 이렇게 안만드는것이 좋다.
	//세션에는 보통 로그인정보만넣으니까 아이디비번만쓰는데 겹칠일이 없기는하다.
	//그런데도 만약 겹친다면, 자동으로 꺼내주는 순서가 있다.
	//저장소의 범위가 작은 순서대로 꺼낸다.
	//page -> request -> session -> application
	//그래서 범위가 작은애를 먼저 꺼낸다.
	//그런데 사실 이름만 잘 지으면 이 우선순위를 고민할 필요는 없기는 하다. 
	session.setAttribute("name", "김길동");
	
	//EL을 사용하지 않으면 이렇게 name변수에 넣어주고 써야한다
	String name = (String)request.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- EL을 사용하면 저장소에 들어있는 속성명을 자동으로 찾아서 출력할 수 있다. -->
	${name }<br> <!-- 지금은 request가 session보다 저장소범위가 작으니 request안에있는 홍길동을 출력 -->
	${email }<br>
	
	<!-- 만일 session의 name을 쓰고싶으면 이렇게 써야한다. -->
	${sessionScope.name }<br>
	
	
	<!-- //EL을 사용하지 않으면 이렇게 name변수에 넣어주고 써야한다. -->
	<%=name %><br>
	
	
	<!-- EL의 내장객체중에 ${param}이라는 놈이 있다.
		${param.id} 이러면 
		그러면 get방식으로
		?id=ㅎㅎ 이렇게 되어있는 값들을 가져올 수 있다.
		
		http://localhost:8000/pro14/el/01_index.jsp?id=hihi
	 -->
	 ${param.id }
</body>
</html>