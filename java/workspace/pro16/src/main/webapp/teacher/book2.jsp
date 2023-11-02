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
			method:"get",
			url:'/pro16/teacher2',
			data: {searchWord : searchWord},
			dataType:'JSON',
			success: function(res){
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
</body>
</html>