<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>
        html, body{
            margin:0;
            height:100%;
            
        }
        div, span{
            /* border: 1px solid black; */
        }
        .back{
            width:100%;
            background-color: black;
        }
        #backTop{
            height: 4%;
        }
        #backBottom{
            height: 12%;
        }

        .wrap{
            width: 1200px;
            height: 84%;
            margin : 0 auto;
        }

        .header_wrapper{
            margin:0 auto;
            width: 90%;
            
        }
        #header{
            height: 10%;
            text-align: center;
        }
        #header > div{
            display:inline-block;
        }

        .header_wrapper > #header > .shop_name{
            width: 19%;
            font-size: 20px;
            text-align: left;
        }
        .header_wrapper > #header > .category_species{
            width: 29%;
            text-align: left;
        }
        .header_wrapper > #header > .category_species > div{
            display:inline-block;
            
            font-size: 20px;
            margin-left: 20px;
        }
        .header_wrapper > #header > .category_species > img{
            margin: 0 0 1% 0;
        }
        .header_wrapper > #header > .searchBar{
            width: 39%;
        }
        .header_wrapper > #header > .searchBar > .jb{
            width: 100%;
            height: 50px;
            background-color: #c3c3c3;
            margin: 0 auto;
            border-radius: 25px;
        }
        .header_wrapper > #header > .icon_wrapper{
            width: 9%;
        }

        .header_wrapper > #header > .icon_wrapper > img{
            margin: 0px 5px;
        }
        #body{
            height: 70%;
            background-color: brown;
        }
        #footer{
            height: 20%;
            background-color: brown;
        }


    </style>
</head>
<body>
    

    <%@ include file="backTop.jsp"%>
    <div class="wrap">
        <div class="header_wrapper">
            <div id="header">
                <div class="shop_name"> 
                    <h2>쇼핑몰 이름</h2> 
                </div>
                <div class="category_species">
                    <div>강아지</div><img src="../img/menuDownArrow.png">
                    <div>고양이</div><img src="../img/menuDownArrow.png">
                    <div>기타</div><img src="../img/menuDownArrow.png">
                </div>
                <div class="searchBar">
                    <div class="jb"></div>
                </div>
                <div class="icon_wrapper">
                    <img src="../img/shopingCart.png" alt="">
                    <img src="../img/user.png" alt="">
                </div>
            </div>
        </div>
        <div id="body">
    
        </div>
        <div id="footer">
            
        </div>
    </div>
    <%@ include file="backBottom.jsp"%>

</body>
</html>