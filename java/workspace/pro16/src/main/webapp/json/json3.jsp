<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(function(){
		$('#checkJson').click(function(){
			$.ajax({
				type: "post",
				async: false,
				/* 아래 주소로 요청 전송 */
				url:"/pro16/json3",
				/* 성공적으로 응답받은 경우 success의 function을 수행 */
				success: function(data, textStatus){
					/* 응답받은 data를 파싱 */
					var jsonInfo = JSON.parse(data);
					
					var memberInfo = "회원 정보<br>";
					memberInfo += "=====<br>";
					
					/* JSON객체 내의 members라는 JSONArray를 순회 */
					for(var i in jsonInfo.members){
						memberInfo += "이름: " + jsonInfo.members[i].name + "<br>";
						memberInfo += "나이: " + jsonInfo.members[i].age + "<br>";
						memberInfo += "성별: " + jsonInfo.members[i].gender + "<br>";
						memberInfo += "별명: " + jsonInfo.members[i].nickname + "<br><br><br>";
					}
					
					var booksInfo = "<br><br><br>도서 정보<br>";
					booksInfo += "=========<br>";
					
					/* JSON객체 내의 books라는 JSONArray를 순회 */
					for(var i in jsonInfo.books){
						booksInfo += "제목: " + jsonInfo.books[i].title + "<br>";
						booksInfo += "저자: " + jsonInfo.books[i].writer + "<br>";
						booksInfo += "가격: " + jsonInfo.books[i].price + "원 <br>";
						booksInfo += "장르: " + jsonInfo.books[i].genre + "<br>";
						imageURL = jsonInfo.books[i].image;
						booksInfo += "<img src=" + imageURL + " />" + "<br><br><br>";
					}
					
					$('#output').html(memberInfo + "<br>" + booksInfo);
				},
				error: function(data, text){
					alert("에러 발생");
				},
				complete: function(data, text){}
			});
		});
	});
</script>
</head>
<body>
	<input type='button' id='checkJson' value='checkJson'/>
	<p id='output'></p>
</body>
</html>