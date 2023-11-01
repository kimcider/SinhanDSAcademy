<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<script>
	$(function(){
		$("#checkJson").click(function(){
			var _jsonInfo = '{"name":"박지성", "age":"25", "gender":"남자", "nickname":"날쌘돌이"}';
			$.ajax({
				type:"post",
				async:false,
				url:"/pro16/json",
				data: {jsonInfo: _jsonInfo},
				success:function(data, textStatus){},
				error:function(data, textStatus){
					alert("에러 발생");
				},
				complete:function(data, textStatus){}
			});
		});
	})
</script>
</head>
<body>
	<input type='button' id='checkJson' value='checkJson'/>
</body>
</html>