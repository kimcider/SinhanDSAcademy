<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title></title>
    <META name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="css/style.css">

    <script src="./js/main.js"></script>
    <script src="./js/script.js"></script>
</head>

<body>
    <div class="wrap">
        <div class="header">
            <div class="size">
                <div><a href="index.html"><img src="img/logo.png" alt=""></a></div>
                <div class="idRelated">
                    <a href="">로그인</a> |
                    <a href="">회원가입</a>
                </div>
            </div>
        </div>

        <div class="menu">
            <ul class="depth1">
                <li>
                    <a href="">회사소개</a>
                    <ul class="depth2">
                        <li><a href="">MENU1_1</a></li>
                        <li><a href="">MENU1_2</a></li>
                        <li><a href="howToCome.do">오시는길</a></li>
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

        <div class="visual swiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="img/visual1.png" alt=""></div>
                <div class="swiper-slide"><img src="img/visual2.png" alt=""></div>
                <div class="swiper-slide"><img src="img/visual3.png" alt=""></div>
                <div class="swiper-slide"><img src="img/visual4.png" alt=""></div>
            </div>

        </div>

        <div class="container">
            <div class="size">
                <div class="section"><img src="img/section1_1.png" alt=""></div>
                <div class="section"><img src="img/section1_2.png" alt=""></div>
                <div class="section"><img src="img/section1_3.png" alt=""></div>
                <div class="section"><img src="img/section1_4.png" alt=""></div>
                <div class="section"><img src="img/section1_5.png" alt=""></div>
                <div class="section"><img src="img/section1_6.png" alt=""></div>
            </div>
        </div>

        <div class="outLink">
            <div class="contents">
                <div class="board_title on">공지사항</div>
                <div class="board_title">자료실</div>
                <div class="board_contents on">
                    <ul>
                        <li>공지사항1입니다 <span>2023-10-18</span></li>
                        <li>공지사항2입니다 <span>2023-10-18</span></li>
                        <li>공지사항3입니다 <span>2023-10-18</span></li>
                        <li>공지사항4입니다 <span>2023-10-18</span></li>
                        <li>공지사항5입니다 <span>2023-10-18</span></li>
                        <li>공지사항6입니다 <span>2023-10-18</span></li>
                    </ul>
                </div>
                <div class="board_contents">
                    <ul>
                        <li>자료실글1입니다 <span>2023-10-11</span></li>
                        <li>자료실글2입니다 <span>2023-10-11</span></li>
                        <li>자료실글3입니다 <span>2023-10-11</span></li>
                        <li>자료실글4입니다 <span>2023-10-11</span></li>
                        <li>자료실글5입니다 <span>2023-10-11</span></li>
                        <li>자료실글6입니다 <span>2023-10-11</span></li>
                    </ul>
                </div>
            </div>

            <div class="contents">
                <iframe src="http://www.youtube.com/embed/4CTKsqT4y64" width="100%" height="360"></iframe>
            </div>
        </div>

        <div class="footer">
            <div class="size">
                <div class="info">
                    <p>신한DS 금융 SW 아카데미</p>
                    <p>서울시 마포구 월드컵북로 4길 77</p>
                    <p>02-123-4567 | 02-380-4444</p>
                    <p>대표자: 서민구 | 개인정보책임자: 아무개</p>
                    <p>사업자등록번호 111-02-1244</p>
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