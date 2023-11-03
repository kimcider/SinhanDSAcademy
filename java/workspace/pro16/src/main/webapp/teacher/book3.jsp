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
			//bookList.jsp를 응답으로서 받기에 dataType은 HTML
			dataType:'HTML',
			success: function(res){
				// teacher2.java에서 응답받은 bookList.jsp코드 자체를 #bookArea의 html값으로 넣어버리는것!
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