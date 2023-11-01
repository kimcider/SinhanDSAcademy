<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function(){
		$('#submit').click(function(){
			var _bookInfo = $('#input').val();
			$.ajax({
				type: "post",
				async: false,
				url:"/pro16/target",
				data:{title: $('#input').val()},
				success: function(data, textStatus){
					var jsonInfo = JSON.parse(data);
					var bookInfo = "책 정보<br>";
					bookInfo += "=====<br>";
					
					bookInfo += "제목: " + jsonInfo.title + "<br>";
					bookInfo += "저자: " + jsonInfo.author + "<br>";
					bookInfo += "출판사: " + jsonInfo.publisher + "<br>";
					console.log(bookInfo);
					console.log(jsonInfo.image);
					$('#textbox').html(bookInfo);
					$('#img').attr('src', jsonInfo.image);
					$('#imglink').attr('href', jsonInfo.link);
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
	<input type="text" id='input' />
	<input type="button" id='submit' value='검색'>
	<div id='output'>
		<div id='imgbox'><a id='imglink' target="_blank"><img id='img'></a></div>
		<div id='textbox'></div>
	</div>
</body>
</html>