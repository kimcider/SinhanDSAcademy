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
			url:'/pro16/teacher',
			data: {searchWord : searchWord},
			dataType:'JSON',
			success: function(res){
				let html = "<table border='1'>";
				html += "<tr>";
				html += "	<td>이미지</td>";
				html += "	<td>책 제목</td>";
				html += "	<td>저자명</td>";
				html += "	<td>출판사</td>";
				html += "</tr>";
				res.items.forEach(item => {
					html += "<tr>";
					html += "	<td><a href='"+item.link+"' target='_blank'><img src='"+item.image+"'width=300></a></td>";
					html += "	<td>"+item.title+"</td>";
					html += "	<td>"+item.author+"</td>";
					html += "	<td>"+item.publisher+"</td>";
					html += "</tr>";
					
				});
				html += "</table>";
				$("#bookArea").html(html);
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