// + 버튼에 이벤트리스너 추가
$('.add_btn').on('click', addProduct);

// +버튼을 누르면 수행
function addProduct() {
    let newTr = $(".addtr").eq(0).clone();

    $(newTr).find('input[name=cnt4]').val('')
    $(newTr).find('img').attr('src', 'img/minus_ico.png');
    $(newTr).find('a').on('click', removeProduct);  

    newTr.appendTo($('tbody'));
}