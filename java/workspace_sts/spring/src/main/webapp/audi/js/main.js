$(function () {
    var swiper = new Swiper(".visual", {
        autoplay: {
            display: 300
        },
        loop: true
    });

    $('.board_title').on('click', function () {
        $('.board_title').toggleClass('on');
        $('.board_contents').toggleClass('on');
    })
})