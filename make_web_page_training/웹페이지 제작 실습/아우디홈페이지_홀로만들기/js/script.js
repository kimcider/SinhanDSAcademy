$(function () {
    $('.depth1 > li').on('mouseover', function () {
        $(this).find('.depth2').stop().slideDown(300);
    })
    $('.depth1 > li').on('mouseout', function () {
        $(this).find('.depth2').stop().slideUp(300);
    })
})