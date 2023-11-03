<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<!-- 본래는 경로를 절대경로로 쓰려면 이렇게 써야한다. -->
	<!-- <form method="post" action="/pro17/member/modMember.do"> -->
	<!-- 프로젝트 명을 프로젝트 이름이 바뀔 때마다 바꾸고싶지 않을 경우 이런식으로 사용해도 된다.  -->
	<!--   <form method="post" action="${pageContext.request.contextPath }/member/modMember.do"> -->

	<!-- 위는 겁나 기니까 이런식으로 편하게 줄 수 있다. -->
	<form method="post" action="<c:url value="/member/modMember.do"/>">
		<h1 style="text-align: center">회원 강비창</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">아이디</p></td>

				<!-- 보통 아이디는 이렇게 표기만 하고 hidden으로 준다 -->
				<td width="400">${memInfo.id }</td>
				<input type='hidden' name='id' value='${memInfo.id }'>
			</tr>
			<tr>
				<td width="200"><p align="right">비밀번호</p></td>
				<td width="400"><input type="password" name="pwd" value='${memInfo.pwd }'></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이름</p></td>
				<td width="400"><input type="text" name="name"
					value="${memInfo.name }"></td>
			</tr>
			<tr>
				<!-- 
					이메일을 수정하지 않으려고 해도, 우리가 만든 modMember코드에서 모두 수정하기 떄문에
					본래 값을 적어줘서 수정하지 않을 경우 기본 값으로 수정되도록 한다.
				 -->
				<td width="200"><p align="right">이메일</p></td>
				<td width="400"><input type="text" name="email"
					value="${memInfo.email }"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">가입일</p></td>

				<!-- 보통 아이디는 이렇게 표기만 하고 hidden으로 준다 -->
				<td width="400">${memInfo.joinDate }</td>
				<input type='hidden' name='id' value='${memInfo.joinDate }'>
			</tr>
			<tr>
				<td width="200"><p>&nbsp;</p></td>
				<td width="400"><input type="submit" value="수정하기"> <input
					type="reset" value="다시 입력"></td>
			</tr>
		</table>
	</form>
</body>
</html>