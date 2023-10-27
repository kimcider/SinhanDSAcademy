// 비용계산 버튼에 이벤트리스너 추가
let calculateButton = document.querySelector('#calbtn');
calculateButton.addEventListener('click', calculatePrice);

// 비용계산버튼을 클릭하면 수행
function calculatePrice() {
    // 각 상품의 수량 추출
    let numComputer = Number($('input[name=cnt1]').val());
    let numTV = Number($('input[name=cnt2]').val());
    let numRef = Number($('input[name=cnt3]').val());

    //공급가격 계산
    let price = numComputer * 1000000 + numTV * 1500000 + numRef * 2000000;

    $.each($('.addtr'), function (index, element) {
        //상품명 추출
        let productName = $(element).find('select').val();
        let productQuantity = Number($(element).find('input').val());
        switch (productName) {
            case '홈시어터':
                price = price + productQuantity * 100000;
                break;
            case '전자렌지':
                price = price + productQuantity * 200000;
                break;
            case '세탁기':
                price = price + productQuantity * 500000;
                break;
        }
    });

    //공급가격 출력
    $('#price1').html(Math.floor(price));

    //세금 계산&출력
    let vat = price * 0.1;
    $('#vat').html(Math.floor(vat));


    //총 견적 계산&출력
    price = price + vat;
    if (price > 3000000) {
        price = price * 0.8;
    } else if (price > 2000000) {
        price = price * 0.9;
    }
    $('#total').html(Math.floor(price));
}