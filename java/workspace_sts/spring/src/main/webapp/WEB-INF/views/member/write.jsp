<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="regist.do" method="post">
		아이디: <input type="text" name="id"><br>
		이름 : <input type="text" name="name"><br>
		취미 : 
			<input type="checkbox" value="게임" name="hobbyname">게임
			<input type="checkbox" value="영화" name="hobbyname">영화
			<input type="checkbox" value="독서" name="hobbyname">독서
			<input type="checkbox" value="낚시" name="hobbyname">낚시
			<input type="checkbox" value="널뛰기" name="hobbyname">널뛰기<br>
		<input type="submit" value="등록">
	</form>
</body>
</html>