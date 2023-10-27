// 비용계산 버튼에 이벤트리스너 추가
let calculateButton = document.querySelector('#calbtn');
calculateButton.addEventListener('click', calculatePrice);

// 비용계산버튼을 클릭하면 수행
function calculatePrice() {
    // 각 상품의 수량 추출
    let numComputer = Number(document.querySelector('input[name="cnt1"]').value);
    let numTV = Number(document.querySelector('input[name="cnt2"]').value);
    let numRef = Number(document.querySelector('input[name="cnt3"]').value);

    //공급가격 계산
    let price = numComputer * 1000000 + numTV * 1500000 + numRef * 2000000;

    // 추가 상품의 수량 추출
    let addTr = document.querySelectorAll('.addtr');
    for (let i = 0; i < addTr.length; i++) {
        let addTrSelect = addTr[i].querySelector('select');

        //상품명과 수량 추출
        let productName = addTrSelect.options[addTrSelect.selectedIndex].value;
        let numberOfProduct = Number(addTr[i].querySelector('input').value);

        //가격계산
        switch (productName) {
            case '홈시어터':
                price = price + numberOfProduct * 100000;
                break;
            case '전자렌지':
                price = price + numberOfProduct * 200000;
                break;
            case '세탁기':
                price = price + numberOfProduct * 500000;
                break;
        }
    }

    //공급가격 출력
    document.querySelector('#price1').innerHTML = Math.floor(price);

    //세금 계산&출력
    let vat = price * 0.1;
    document.querySelector('#vat').innerHTML = Math.floor(vat);


    //총 견적 계산&출력
    price = price + vat;
    if (price > 3000000) {
        price = price * 0.8;
    } else if (price > 2000000) {
        price = price * 0.9;
    }
    document.querySelector('#total').innerHTML = Math.floor(price);
}