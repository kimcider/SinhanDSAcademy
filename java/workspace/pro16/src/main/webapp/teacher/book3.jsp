<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function() {
	$("#searchBtn").click(function(){
		let searchWord = $("#searchWord").val();
		$.ajax({
			method:"post",
			url:'/pro16/teacher2',
			data: {searchWord : searchWord},
			///여기를 HTML로 바꾸면 이런식으로도 가능하다
			//teacher2로 들어가서 bookList.jsp를 받아온 후, 그 HTML 자체를 div요소에 넣어버리는것. 
			//웹사이트면 이런식으로 쓰는게 편하다. 
			dataType:'HTML',
			success: function(res){
				$("#bookArea").html(res);
			}
			
		})
	});
});

</script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="searchWord">
	<input type="button" id="searchBtn" value="조회">
	<div id="bookArea"></div>
</body>
</html>