<!-- Ajax로 Json객체 받아오기 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<script>
	$(function(){
		$('#checkJson').click(function(){
			$.ajax({
				type: "post",
				async: false,
				/* 아래 주소로 요청 전송 */
				url:"/pro16/json2",
				/* 성공적으로 응답받은 경우 success의 function을 수행 */
				success: function(data, textStatus){
					/* 응답받은 data를 파싱 */
					var jsonInfo = JSON.parse(data);
					
					var memberInfo = "회원 정보<br>";
					memberInfo += "=====<br>";
					/* Json객체를 바탕으로 html문 수정 */
					for(var i in jsonInfo.members){ /* 응답받은 JSON객체 내의 members라는 JSONArray를 순환 */
						memberInfo += "이름: " + jsonInfo.members[i].name + "<br>";
						memberInfo += "나이: " + jsonInfo.members[i].age + "<br>";
						memberInfo += "성별: " + jsonInfo.members[i].gender + "<br>";
						memberInfo += "별명: " + jsonInfo.members[i].nickname + "<br><br><br>";
					}
					$('#output').html(memberInfo);
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
	<h1 id='output'></h1>
</body>
</html>