<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//여기다 선언하고 b에서 사용하면?
		/*
			b.jsp파일로 가봐라
			b.jsp 파일에서는 에러가 나지만 실제로 출력을 해 보면 출력이 잘 된다.
			b.jsp의 소스를 그대로 가져다가 a.jsp의 그 부분에 넣는다고 생각하면 된다.
		*/
	String name = "홍길동";
	
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 a.jsp</h1>
	
	
	<!-- 이렇게 a.jsp 사이에 b.jsp를 불러올 수 있다. 
		디렉티브 방식  -->
	<!-- 
	반드시 외워야 할 주의사항!!!!
		주의할점은 이 코드는 서버에서 실행되는것이다. 
		얘는 서버에서 동작하는 코드이기 때문에 /pro11/이렇게 쓰면 안된다!
		클라이언트에서 경로를 쓸 때는 contextPath를 써야만한다.
	 -->
	<%@ include file="/include/classB.jsp" %>
	
	
	<!-- 다른 인클루드방식
	액션태그 방식 
	근데 이렇게 하면 디렉티브방식처럼 이 파일에서 b.jsp로 name변수를 넘겨주면 사용할 수 없다.


	디렉티브방식은 b.jsp소스를 그대로 카피해서 넣는거고, 
	액션태그방식은 b.jsp를 실행해서 그 결과를 가져오는거다.
		그래서 액션태그 방식은 변수 공유가 안된다. 
		

		두 방식 모두 알아둬야 한다!!!!
	b.jsp에서 String name을 선언하면 전혀 문제가 생기지 않는다. 
	-->
	<jsp:include page="classB.jsp"/>
	
	<!-- 		액션태그방식의 장점
			태그와 태그 사이에 또 다른 태그를 넣어서 파라미터를 전달할 수 있다.
			
			보통 선생님은 디렉티브 방식을 사용하고
			액션태그는 파라미터를 넘겨줘야 할 때만 사용한다.  -->
	<jsp:include page="classB.jsp">
		<jsp:param name="email" value="rlaqucjs96@naver.com"> 
	</jsp:include>
	<h1>여기는 a.jsp</h1>
	
	<script>
		//이렇게 클라이언트에서 경로를 쓸 때는 contextPath(pro11)를 무조건 써야한다.
		// location.href="/pro11/include/b.jsp";
	</script>
</body>
</html>