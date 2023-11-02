<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Document</title>

<link rel="stylesheet" href="style2.css">
<script src="jquery-3.4.1.js"></script>
</head>
<body>
	<!-- 꾸밈용 회전하는 박스 -->
	<!-- 
	<div class="winner">
		<div class="top"></div>
		<div>
			<span style="-i: 0;">
				<h2 style="color: white">WHO'S WINNER</h2>
			</span> <span style="-i: 1;">
				<h2 style="color: white">WHO'S WINNER</h2>
			</span> <span style="-i: 2;">
				<h2 style="color: white">WHO'S WINNER</h2>
			</span> <span style="-i: 3;">
				<h2 style="color: white">WHO'S WINNER</h2>
			</span>
		</div>
	</div>
	 -->
	<!-- bgm on/off -->
	<!-- 
	<audio preload="auto" onloadstart="this.volume=0.05" loop="true"
		id="player" src="bgm_baduk.mp3"></audio>
	<div class="button" style='width: 80px; float: right;'>
		<button onclick="document.getElementById('player').play()"
			style="background-color: black; color: whitesmoke">BGM on</button>
		<button onclick="document.getElementById('player').pause()"
			style="background-color: black; color: dimgrey">BGM off</button>
	</div>
 	-->
 	
	<div class="board">
		<c:forEach var="row" begin="0" end="18" step="1">
			<div class="board-row">
				<c:forEach var="col" begin="0" end="18" step="1">
					<button  type="button"
						class="trans_button" data-row="${row}" data-col="${col }">
						<img src="img/board_empty.jpg" alt="placeholder">
					</button>
				</c:forEach>
			</div>
		</c:forEach>
	</div>

	<!-- 버튼 이벤트 -->
	<script>
		var turn = 0; // 0이 검은돌
		$('.trans_button').on('click', function(){
			var row = $(this).data('row');
			var col = $(this).data('col');
			var temp = $(this);
			$.ajax({
				type : "post",
				async : true,
				url : "/omok/controller",
				data : {
					row : $(this).data('row'),
					col : $(this).data('col'),
					turn : turn
				},
				success : function(data, testStatus){
					if(data == 'victory'){
						console.log("ㅎㅎ");
						if(turn == 0){
							temp.find('img').attr('src', 'img/board_black.jpg');
						}
						if(turn == 1){
							temp.find('img').attr('src', 'img/board_white.jpg');
						}
						console.log("끝");
						if(turn == 0) setTimeout("alert('black win')", 200);
						if(turn == 1) setTimeout("alert('white win')", 200);
					}
					if(data == 'placable'){
						if(turn == 0){
							temp.find('img').attr('src', 'img/board_black.jpg');
						}
						if(turn == 1){
							temp.find('img').attr('src', 'img/board_white.jpg');
						}
						turn = (turn + 1) % 2;
					}else{
						
					}
				},
				error : function(xhr, status, error){
					alert("에러 발생");
				},
				complete : function(data, textStatus){
					
				}
			});
		});
	</script>

	<script>
		var sound1 = new Audio();
		sound1.src = "wind_sound.mp3"
	</script>

	<!-- 캐릭터의 버블 이펙트 -->
	<!-- 
	<div class="effect">
		<div class="bubbles"
			style="position: relative; left: 121px; top: 450px;"></div>
		<div class="bubbles"
			style="position: relative; left: 10px; top: 150px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 10px;"></div>
		<div class="bubbles"
			style="position: relative; left: 20px; top: 120px;"></div>
		<div class="bubbles"
			style="position: relative; left: 25px; top: 110px;"></div>
		<div class="bubbles"
			style="position: relative; left: 30px; top: 102px;"></div>
		<div class="bubbles"
			style="position: relative; left: 50px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 55px; top: 60px;"></div>
		<div class="bubbles"
			style="position: relative; left: 78px; top: 17px;"></div>
		<div class="bubbles"
			style="position: relative; left: 92px; top: 78px;"></div>
		<div class="bubbles"
			style="position: relative; left: 19px; top: 89px;"></div>
		<div class="bubbles"
			style="position: relative; left: 90px; top: 19px;"></div>
		<div class="bubbles"
			style="position: relative; left: 120px; top: 50px;"></div>
		<div class="bubbles"
			style="position: relative; left: 112px; top: 91px;"></div>
		<div class="bubbles"
			style="position: relative; left: 135px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 145px; top: 193px;"></div>
		<div class="bubbles"
			style="position: relative; left: 170px; top: 137px;"></div>
		<div class="bubbles"
			style="position: relative; left: 180px; top: 172px;"></div>
		<div class="bubbles"
			style="position: relative; left: 155px; top: 119px;"></div>
		<div class="bubbles"
			style="position: relative; left: 142px; top: 139px;"></div>
		<div class="bubbles"
			style="position: relative; left: 121px; top: 143px;"></div>
		<div class="bubbles"
			style="position: relative; left: 89px; top: 111px;"></div>
		<div class="bubbles"
			style="position: relative; left: 39px; top: 103px;"></div>
		<div class="bubbles"
			style="position: relative; left: 104px; top: 56px;"></div>
		<div class="bubbles"
			style="position: relative; left: 90px; top: 29px;"></div>
		<div class="bubbles"
			style="position: relative; left: 70px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 161px; top: 43px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 45px;"></div>
		<div class="bubbles"
			style="position: relative; left: 189px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 101px; top: 49px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 450px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 3px; top: 150px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 4px; top: 10px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 6px; top: 120px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 2px; top: 110px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 8px; top: 102px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 2px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 8px; top: 60px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 7px; top: 17px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 9px; top: 78px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 89px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 9px; top: 19px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 12px; top: 50px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 91px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 13px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 15px; top: 193px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 7px; top: 137px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 8px; top: 172px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 5px; top: 119px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 2px; top: 139px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 143px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 111px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 3px; top: 103px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 5px; top: 56px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 9px; top: 29px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 7px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 6px; top: 43px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 2px; top: 45px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 8px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 49px;"></div>
		<div class="bubbles"
			style="position: relative; left: 20px; top: 450px;"></div>
		<div class="bubbles"
			style="position: relative; left: 1px; top: 150px;"></div>
		<div class="bubbles" style="position: relative; left: 1px; top: 10px;"></div>
		<div class="bubbles"
			style="position: relative; left: 2px; top: 120px;"></div>
		<div class="bubbles"
			style="position: relative; left: 2px; top: 110px;"></div>
		<div class="bubbles"
			style="position: relative; left: 3px; top: 102px;"></div>
		<div class="bubbles" style="position: relative; left: 5px; top: 34px;"></div>
		<div class="bubbles" style="position: relative; left: 5px; top: 60px;"></div>
		<div class="bubbles" style="position: relative; left: 7px; top: 17px;"></div>
		<div class="bubbles" style="position: relative; left: 9px; top: 78px;"></div>
		<div class="bubbles"
			style="position: relative; left: 19px; top: 89px;"></div>
		<div class="bubbles" style="position: relative; left: 9px; top: 19px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 50px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 91px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 193px;"></div>
		<div class="bubbles"
			style="position: relative; left: 10px; top: 137px;"></div>
		<div class="bubbles"
			style="position: relative; left: 10px; top: 172px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 119px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 139px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 143px;"></div>
		<div class="bubbles"
			style="position: relative; left: 9px; top: 111px;"></div>
		<div class="bubbles"
			style="position: relative; left: 3px; top: 103px;"></div>
		<div class="bubbles"
			style="position: relative; left: 14px; top: 56px;"></div>
		<div class="bubbles" style="position: relative; left: 9px; top: 29px;"></div>
		<div class="bubbles" style="position: relative; left: 7px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 16px; top: 43px;"></div>
		<div class="bubbles" style="position: relative; left: 1px; top: 45px;"></div>
		<div class="bubbles"
			style="position: relative; left: 18px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 11px; top: 49px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 0px; top: 450px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 31px; top: 150px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 41px; top: 10px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 61px; top: 120px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 21px; top: 110px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 81px; top: 102px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 21px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 81px; top: 60px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 71px; top: 17px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 91px; top: 78px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 89px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 91px; top: 19px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 2px; top: 50px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 91px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 3px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 5px; top: 193px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 71px; top: 137px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 81px; top: 172px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 51px; top: 119px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 21px; top: 139px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 143px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 111px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 31px; top: 103px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 51px; top: 56px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 91px; top: 29px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 71px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 61px; top: 43px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 21px; top: 45px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 81px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 49px;"></div>
	</div>
	<div class="effect2">
		<div class="bubbles"
			style="position: relative; left: 141px; top: 450px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 150px;"></div>
		<div class="bubbles"
			style="position: relative; left: 55px; top: 10px;"></div>
		<div class="bubbles"
			style="position: relative; left: 50px; top: 120px;"></div>
		<div class="bubbles"
			style="position: relative; left: 55px; top: 110px;"></div>
		<div class="bubbles"
			style="position: relative; left: 50px; top: 102px;"></div>
		<div class="bubbles"
			style="position: relative; left: 75px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 75px; top: 60px;"></div>
		<div class="bubbles"
			style="position: relative; left: 89px; top: 17px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 78px;"></div>
		<div class="bubbles"
			style="position: relative; left: 28px; top: 89px;"></div>
		<div class="bubbles"
			style="position: relative; left: 192px; top: 19px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 50px;"></div>
		<div class="bubbles"
			style="position: relative; left: 11px; top: 91px;"></div>
		<div class="bubbles"
			style="position: relative; left: 13px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 14px; top: 193px;"></div>
		<div class="bubbles"
			style="position: relative; left: 17px; top: 137px;"></div>
		<div class="bubbles"
			style="position: relative; left: 18px; top: 172px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 119px;"></div>
		<div class="bubbles"
			style="position: relative; left: 14px; top: 139px;"></div>
		<div class="bubbles"
			style="position: relative; left: 12px; top: 143px;"></div>
		<div class="bubbles"
			style="position: relative; left: 8px; top: 111px;"></div>
		<div class="bubbles"
			style="position: relative; left: 3px; top: 103px;"></div>
		<div class="bubbles"
			style="position: relative; left: 10px; top: 56px;"></div>
		<div class="bubbles"
			style="position: relative; left: 192px; top: 29px;"></div>
		<div class="bubbles"
			style="position: relative; left: 71px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 61px; top: 43px;"></div>
		<div class="bubbles"
			style="position: relative; left: 21px; top: 45px;"></div>
		<div class="bubbles"
			style="position: relative; left: 89px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 10px; top: 49px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 12px; top: 450px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 34px; top: 150px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 64px; top: 10px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 65px; top: 120px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 26px; top: 110px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 68px; top: 102px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 26px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 68px; top: 60px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 77px; top: 17px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 97px; top: 78px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 14px; top: 89px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 94px; top: 19px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 112px; top: 50px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 111px; top: 91px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 113px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 151px; top: 193px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 71px; top: 137px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 18px; top: 172px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 51px; top: 119px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 12px; top: 139px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 143px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 131px; top: 111px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 33px; top: 103px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 53px; top: 56px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 95px; top: 29px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 57px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 66px; top: 43px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 26px; top: 45px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 18px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 61px; top: 49px;"></div>
		<div class="bubbles"
			style="position: relative; left: 2px; top: 450px;"></div>
		<div class="bubbles"
			style="position: relative; left: 19px; top: 150px;"></div>
		<div class="bubbles"
			style="position: relative; left: 61px; top: 10px;"></div>
		<div class="bubbles"
			style="position: relative; left: 32px; top: 120px;"></div>
		<div class="bubbles"
			style="position: relative; left: 28px; top: 110px;"></div>
		<div class="bubbles"
			style="position: relative; left: 31px; top: 102px;"></div>
		<div class="bubbles"
			style="position: relative; left: 56px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 15px; top: 60px;"></div>
		<div class="bubbles"
			style="position: relative; left: 71px; top: 17px;"></div>
		<div class="bubbles"
			style="position: relative; left: 19px; top: 78px;"></div>
		<div class="bubbles" style="position: relative; left: 9px; top: 89px;"></div>
		<div class="bubbles"
			style="position: relative; left: 93px; top: 19px;"></div>
		<div class="bubbles"
			style="position: relative; left: 26px; top: 50px;"></div>
		<div class="bubbles"
			style="position: relative; left: 21px; top: 91px;"></div>
		<div class="bubbles"
			style="position: relative; left: 18px; top: 34px;"></div>
		<div class="bubbles"
			style="position: relative; left: 23px; top: 193px;"></div>
		<div class="bubbles"
			style="position: relative; left: 101px; top: 137px;"></div>
		<div class="bubbles"
			style="position: relative; left: 103px; top: 172px;"></div>
		<div class="bubbles"
			style="position: relative; left: 151px; top: 119px;"></div>
		<div class="bubbles"
			style="position: relative; left: 121px; top: 139px;"></div>
		<div class="bubbles"
			style="position: relative; left: 132px; top: 143px;"></div>
		<div class="bubbles"
			style="position: relative; left: 91px; top: 111px;"></div>
		<div class="bubbles"
			style="position: relative; left: 35px; top: 103px;"></div>
		<div class="bubbles"
			style="position: relative; left: 141px; top: 56px;"></div>
		<div class="bubbles"
			style="position: relative; left: 95px; top: 29px;"></div>
		<div class="bubbles"
			style="position: relative; left: 71px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 61px; top: 43px;"></div>
		<div class="bubbles"
			style="position: relative; left: 110px; top: 45px;"></div>
		<div class="bubbles"
			style="position: relative; left: 71px; top: 30px;"></div>
		<div class="bubbles"
			style="position: relative; left: 101px; top: 49px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 10px; top: 450px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 103px; top: 150px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 71px; top: 10px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 15px; top: 120px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 71px; top: 110px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 18px; top: 102px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 80px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 16px; top: 60px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 17px; top: 17px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 9px; top: 78px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 111px; top: 89px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 9px; top: 19px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 29px; top: 50px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 18px; top: 91px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 38px; top: 34px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 53px; top: 193px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 7px; top: 137px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 8px; top: 172px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 5px; top: 119px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 2px; top: 139px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 1px; top: 143px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 111px; top: 111px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 81px; top: 103px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 151px; top: 56px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 9px; top: 29px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 7px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 6px; top: 43px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 11px; top: 45px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 8px; top: 30px;"></div>
		<div class="bubbles2"
			style="position: relative; left: 111px; top: 49px;"></div>
	</div>
	 -->
</body>
</html>