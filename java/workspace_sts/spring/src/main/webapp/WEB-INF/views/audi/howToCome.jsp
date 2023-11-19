<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aef63147fb49c659f9bfd23ffc8e5488&libraries=services"></script>
    <style>
        .container>.size {
            /* border: 1px solid black; */
            width: 1200px;
            height: 600px;

        }

        .container>.size>div {
            width: 50%;
            height: 600px;
            float: left;
            text-align: center;
            border: 1px solid black;
        }
        .container>.size> #greet{
            line-height: 600px;
        }
        .container > .size > div > #map{
            width: 80%;
            height: 80%;
            margin: auto;
            border: black 1px solid;
        }
    </style>
    <script>
        $(function () {
            var mapContainer = document.getElementById('map'); // ì§ëë¥¼ íìí  div 
            var mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // ì§ëì ì¤ì¬ì¢í
                level: 3 // ì§ëì íë ë ë²¨
            };

            // ì§ëë¥¼ ìì±í©ëë¤    
            var map = new kakao.maps.Map(mapContainer, mapOption);

            // ì£¼ì-ì¢í ë³í ê°ì²´ë¥¼ ìì±í©ëë¤
            var geocoder = new kakao.maps.services.Geocoder();

            // ì£¼ìë¡ ì¢íë¥¼ ê²ìí©ëë¤
            geocoder.addressSearch('ìì¸ ë§í¬êµ¬ ìëì»µë¶ë¡4ê¸¸ 77', function (result, status) {

                // ì ìì ì¼ë¡ ê²ìì´ ìë£ëì¼ë©´ 
                if (status === kakao.maps.services.Status.OK) {

                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    // ê²°ê³¼ê°ì¼ë¡ ë°ì ìì¹ë¥¼ ë§ì»¤ë¡ íìí©ëë¤
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    // ì¸í¬ìëì°ë¡ ì¥ìì ëí ì¤ëªì íìí©ëë¤
                    var infowindow = new kakao.maps.InfoWindow({
                        content: '<div style="width:150px;text-align:center;padding:6px 0;">ê¸ìµ SW ìì¹´ë°ë¯¸</div>'
                    });
                    infowindow.open(map, marker);

                    // ì§ëì ì¤ì¬ì ê²°ê³¼ê°ì¼ë¡ ë°ì ìì¹ë¡ ì´ëìíµëë¤
                    map.setCenter(coords);
                }
            });
        })
    </script>
</head>

<body>
    <div class="wrap">
        <div class="header">
            <div class="size">
                <div><a href="index.html"><img src="img/logo.png" alt=""></a></div>
                <div class="idRelated">
                    <a href="">ë¡ê·¸ì¸</a> |
                    <a href="">íìê°ì</a>
                </div>
            </div>
        </div>

        <div class="menu">
            <ul class="depth1">
                <li>
                    <a href="">íì¬ìê°</a>
                    <ul class="depth2">
                        <li><a href="">MENU1_1</a></li>
                        <li><a href="">MENU1_2</a></li>
                        <li><a href="howToCome.html">ì¤ìëê¸¸</a></li>
                    </ul>
                </li>
                <li>
                    <a href="">MENU2</a>
                    <ul class="depth2">
                        <li><a href="">MENU2_1</a></li>
                        <li><a href="">MENU2_2</a></li>
                        <li><a href="">MENU2_3</a></li>
                    </ul>
                </li>
                <li>
                    <a href="">MENU3</a>
                    <ul class="depth2">
                        <li><a href="">MENU3_1</a></li>
                        <li><a href="">MENU3_2</a></li>
                        <li><a href="">MENU3_3</a></li>
                    </ul>
                </li>
                <li>
                    <a href="">MENU4</a>
                    <ul class="depth2">
                        <li><a href="">MENU4_1</a></li>
                        <li><a href="">MENU4_2</a></li>
                        <li><a href="">MENU4_3</a></li>
                    </ul>
                </li>
                <li>
                    <a href="">MENU5</a>
                    <ul class="depth2">
                        <li><a href="">MENU5_1</a></li>
                        <li><a href="">MENU5_2</a></li>
                        <li><a href="">MENU5_3</a></li>
                    </ul>
                </li>
            </ul>
        </div>


        <div class="container">
            <div class="size">
                <div id="greet">
                    <h1>ì¤ìëê¸¸</h1>
                </div>
                <div>
                    <div id="map"></div>
                </div>
            </div>
        </div>


        <div class="footer">
            <div class="size">
                <div class="info">
                    <p>ì íDS ê¸ìµ SW ìì¹´ë°ë¯¸</p>
                    <p>ìì¸ì ë§í¬êµ¬ ìëì»µë¶ë¡ 4ê¸¸ 77</p>
                    <p>02-123-4567 | 02-380-4444</p>
                    <p>ëíì: ìë¯¼êµ¬ | ê°ì¸ì ë³´ì±ìì: ìë¬´ê°</p>
                    <p>ì¬ììë±ë¡ë²í¸ 111-02-1244</p>
                </div>

                <div class="sns_area">
                    <a href="" target="_blank"><img src="img/blog_ico.png" alt=""></a>
                    <a href="" target="_blank"><img src="img/facebook_ico.png" alt=""></a>
                    <a href="" target="_blank"><img src="img/insta_ico.png" alt=""></a>
                </div>
            </div>
        </div>
    </div>
</body>

</html>