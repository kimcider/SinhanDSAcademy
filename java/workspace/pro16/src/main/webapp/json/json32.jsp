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
				url:"/pro16/json3",
				// dataType: 'JSON',  //이렇게 쓰면 안된다!!!
				// 이렇게 쓰면 문자열로 나오는게 아니라 객체로나온다. 
				// dataType을 'JSON'으로 지정하면 JSON.parse(data)를 안해도 된다.
				// datatype을 지정해주지 않으면 그냥 문자열이 들어온다.
				// JSON.parse를 하거나, dataType을 JSON으로 지정할 경우, 알아서 JSON타입으로 파싱이된다.
				dataType: 'JSON',
				success: function(data, textStatus){
					//var jsonInfo = JSON.parse(data);
					var jsonInfo = data
					var memberInfo = "회원 정보<br>";
					memberInfo += "=====<br>";
					
					for(var i in jsonInfo.members){
						//dataType을 이렇게해놓을 경우, 그냥 jsonInfo를 안쓰고 이렇게 써도된다. ㅇㅇ
						//memberInfo += "이름: " + data.members[i].name + "<br">;
						memberInfo += "이름: " + jsonInfo.members[i].name + "<br>";
						memberInfo += "나이: " + jsonInfo.members[i].age + "<br>";
						memberInfo += "성별: " + jsonInfo.members[i].gender + "<br>";
						memberInfo += "별명: " + jsonInfo.members[i].nickname + "<br><br><br>";
					}
					var booksInfo = "<br><br><br>도서 정보<br>";
					booksInfo += "=========<br>";
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