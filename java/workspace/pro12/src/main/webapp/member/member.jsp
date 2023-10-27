<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- import class -->
    <%@ page
    import="java.util.*"
    import="memberMaintenance.*" %>
<html>
<head>
	<style>
		text-align: center;
	</style>
</head>
<body>
	<h1>회원 정보 출력</h1>
	<%
		request.setCharacterEncoding("utf-8");
	
		// serach.jsp에서 받아온 name속성의 값을 추출
		String _name = request.getParameter("name");
		
		// VO를 생성해 해당 name을 갖는 MemberVO객체들을 List에 반환받음.
		MemberVO memberVO = new MemberVO();
		memberVO.setName(_name);
		MemberDAO dao = new MemberDAO();
		List membersList=dao.listMembers(memberVO);
	%>
	
	<table border=1 width=800 align=center>
		<tr align=center bgcolor='#FFFF66'>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
		
		<%
			// for문을 사용해 List의 모든 객체들을 table에 출력
			for(int i = 0; i < membersList.size(); i++){
				MemberVO vo = (MemberVO) membersList.get(i);
				String id=vo.getId();
				String pwd=vo.getPwd();
				String name=vo.getName();
		%>
			<tr align=center>
				<td><%=id %></td>
				<td><%=pwd %></td>
				<td><%=name %></td>
				<td><%=vo.getEmail() %></td>
				<td><%=vo.getJoinDate() %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>