<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 545p 
	접두어(prefix)붙혀주기 
	uri는 컨트롤 스페이스바로 core를 선택해주면된다.
	이 한줄 안넣으면 동작을 안한다. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page
	import="java.util.*"
 %>
<%@ page
	import="_02_ELObjectUseExample.*"
 %>
 <%@ page
	import="test.*"
 %>
<%
	// 외부에서 생성된 세션이라고 가정
	session.setAttribute("loginInfo", "홍길동");
	
	
	//session.invalidate();
	//session.removeAttribute("loginInfo");
%>

<!-- 이것도 쓰는 사람이 있기는 하다... -->
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 얘는 태그고, html보다 엄격해서 열었으면 반드시 닫아야한다. -->
	<c:set var="name" value="홍길동"></c:set>
	${name }
	
	<!-- 파라미터 id가 hong이면 안녕하세요 출력하도록 -->
	<c:if test="${param.id == 'hong' }"> 안녕하세요 </c:if>
	<!-- 홍이 아니면 -->
	<c:if test="${param.id != 'hong' }"> 안녕히가세요 </c:if>
	
	<hr>
	<!-- 어떨때는 로그인이, 어떨때는 로그아웃이 나와야 할 떄  -->
	<!-- 로그인은 세션객체에 loginInfo값이 있어야한다. 
		로그아웃은 세션 객체에 loginInfo값이 없어야한다. 
		현재는 위의 코드에서 session.setAttri로 loginInfo속성을 설정했기 때문에
		로그아웃으로 뜬다.
		invalidate를 활성화한 다음에 다음에 다시 접속하면 로그인으로 뜬다.-->
	<c:if test="${empty loginInfo }">
		<button>로그인</button>
	</c:if>
	<c:if test="${!empty loginInfo }">
		<button>로그아웃</button>
	</c:if>
	
	<hr>
	<c:set var="age" value="25"/>
	<c:choose>
		<c:when test="${age >= 20 && age < 30 }">20대</c:when>
		<c:when test="${age >= 30 && age < 40 }">30대</c:when>
		<c:otherwise>그외</c:otherwise>
		
	</c:choose>
	
	<hr>
	<!--  foreach
		
	 -->
	<c:forEach var="i" begin="1" end="5">
		<sapn><h5>${i }</h5></sapn>
	</c:forEach>
	
	
	
	
	<!-- for each로 반복문돌려보기 
	이 코드 매우 중요! 외워야한다! -->
	<%
		//여기는 시간없으니까 그냥 쓰는데 원래 jsp파일 안에는 로직을 넣지 않는다 ㅇㅋ?
		List<MemberVO> list = new ArrayList<>();
		MemberVO vo = new MemberVO();
		vo.setName("홍길동");
		list.add(vo);
		vo = new MemberVO();
		vo.setName("김길동");
		list.add(vo);
		
		request.setAttribute("list", list);
		//보통 여기까지가 서블릿에서 하고 포워드가 될 부분이다.
	%>
	
	<!-- 향상된 포문처럼 쓸 것이기 때문에 
	여기의 변수명은 결국 memberVO의 객체가 들어온다. 
	begin end 필요 없다. 어차피 list내의 맴버들을 싹돌릴꺼니까
	이거 안보고 코딩할 수 있어야한다. -->
	<!-- varStatus란?
		상태변수이다. Status의 변수. 
		varStatus를 설정을 하고, status를 사용하면 변수의 index를 알 수 있다.
	 -->
	<c:forEach var="mvo" items="${list }" varStatus="status">
		${status.index}  ${mvo.name }<br>
		<!--  밑에 나오는 JSPL로 메소드를 호출하는 방법의 활용법 
		게시판에 날짜가 있고, 날짜가 오늘 날짜 기준으로 1주일 이내이다. 이러면, 
		오늘날짜 - db날짜 < 7 이러면 new라고 쓰고싶다. 이 경우에는 if를 써야되는데 그러면 더러워지자나.
		그래서 여기에 간단히 메소드만 호출하고 실제 그 로직은 날짜만 넘겨주면 그 메소드는 new를 리턴을 하든 말든
		그런식으로 하게 할 수 있다. 이게 밑에 나오는 JSPL로 메소드를 호출하는 방법의 활용법 -->
		
		
	</c:forEach>
	<br><br>
	
	<!-- 클라에서동작 so pro14필요 
		이렇게 하면 contextPath명을 바꿀 경우, 코드들을 모두 바꿔야 한다. -->
	<img src="/pro14/img/img.png">
	<!-- 하지만 c:url태그를 쓰면 contextPath를 안써도 되서 매우 편함!!
		이렇게 하면 나중에 contextPath명을 바꿔야할 경우 소스코드를 안바꿔도 된다. -->
	<img src="<c:url value="/img/img.png"/>">
	
	
	<hr>
	
	<!-- taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" 사용해보기
	545p fmt
	fmt를 안쓰면 100000을 정수로 직접 변환시켜서 넣어야하나??  -->
	<%
		request.setAttribute("price", 1000000);
		request.setAttribute("date", new Date());
	%>
	금액 : <fmt:formatNumber value="${price }"/><br>
	<!-- date는 포맷만 알면 된다 DB의 패턴과 비슷하군-->
	일자 : <fmt:formatDate value="${date }" pattern="YYYY-MM-dd hh:mm:ss"/>
	
	
	<hr>
	<!-- JSPL로 메소드를 호출하는 방법 -->
	<!-- test.Test객체가 request에 있다고 가정해보자 -->
	<%
		request.setAttribute("test", new test.Test());
	%>
	<!-- 이렇게 request객체 안에 담긴 객체 안에 담긴 메소드를 호출할 수 있다. -->
	${test.sum(10) } 
	<!-- 함수를 static으로 선언해두면 test객체를 만들지 않고도 사용할 수 있다. -->
	<!-- 이거 왜안되냐 고쳐봐라-->
	<!-- ${Test.checkDate() } -->
</body>
</html>