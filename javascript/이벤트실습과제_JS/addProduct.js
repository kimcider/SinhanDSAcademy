// + 버튼에 이벤트리스너 추가
let addButton = document.querySelector('.add_btn');
addButton.addEventListener('click', addProduct);

// +버튼을 누르면 수행
function addProduct() {
    let newTr = document.createElement('tr');

        // tr의 속성값 생성&추가
        let trClass = document.createAttribute('class');
        trClass.value = 'addtr';
        newTr.setAttributeNode(trClass);

        //th1 추가
        let newTh1 = document.createElement('th');
        newTh1.innerHTML = '추가상품'
        newTr.appendChild(newTh1);

        //td1 추가
        let newTd1 = document.createElement('td');

            // th1의 속성 추가
            let newTd1Class = document.createAttribute('class');
            newTd1Class.value = 'padding_t';
            newTd1.setAttributeNode(newTd1Class);

            // select type의 input태그 추가 
            let newSelect = document.createElement('select');

                // select의 속성 추가
                let newSelectName = document.createAttribute('name');
                newSelectName.value = "product";
                newSelect.setAttributeNode(newSelectName);

                let newSelectClass = document.createAttribute('class');
                newSelectClass.value = "full";
                newSelect.setAttributeNode(newSelectClass);

                // 옵션들 추가
                let newOption1 = document.createElement('option');
                let newOption2 = document.createElement('option');
                let newOption3 = document.createElement('option');
                newOption1.innerHTML = "홈시어터";
                newOption2.innerHTML = "전자렌지";
                newOption3.innerHTML = "세탁기";

                    // 옵션들의 속성 추가
                    let newOption1Value = document.createAttribute('value');
                    let newOption2Value = document.createAttribute('value');
                    let newOption3Value = document.createAttribute('value');
                    newOption1Value.value = "홈시어터";
                    newOption2Value.value = "전자렌지";
                    newOption3Value.value = "세탁기";
                    newOption1.setAttributeNode(newOption1Value)
                    newOption2.setAttributeNode(newOption2Value)
                    newOption3.setAttributeNode(newOption3Value)

                newSelect.appendChild(newOption1);
                newSelect.appendChild(newOption2);
                newSelect.appendChild(newOption3);
            newTd1.appendChild(newSelect);
        newTr.appendChild(newTd1);




        //th2 추가
        let newTh2 = document.createElement('th');
        newTh2.innerHTML = '수량'
        newTr.appendChild(newTh2);




        //td2 추가
        let newTd2 = document.createElement('td');

            //td2의 input태그 추가
            let newTd2Input = document.createElement('input');
            
                //input의 속성들 추가
                let newTd2InputType = document.createAttribute('type');
                newTd2InputType.value = 'number';
                newTd2Input.setAttributeNode(newTd2InputType);

                let newTd2InputName = document.createAttribute('name');
                newTd2InputName.value = 'cnt4';
                newTd2Input.setAttributeNode(newTd2InputName);

                let newTd2InputValue = document.createAttribute('value');
                newTd2InputValue.value = '';
                newTd2Input.setAttributeNode(newTd2InputValue);

                let newTd2InputClass = document.createAttribute('class');
                newTd2InputClass.value = 'txt_r basic04';
                newTd2Input.setAttributeNode(newTd2InputClass);

                let newTd2InputMin = document.createAttribute('min');
                newTd2InputMin.value = '1';
                newTd2Input.setAttributeNode(newTd2InputMin);

                let newTd2InputMax = document.createAttribute('max');
                newTd2InputMax.value = '999';
                newTd2Input.setAttributeNode(newTd2InputMax);

                let newTd2InputMaxLength = document.createAttribute('maxlength');
                newTd2InputMaxLength.value = '3';
                newTd2Input.setAttributeNode(newTd2InputMaxLength);

            newTd2.appendChild(newTd2Input);

            //td2의 a태그 (minus버튼) 추가
            let newTd2A = document.createElement('a');

                //a태그의 속성들 추가
                let newTd2AHref = document.createAttribute('href');
                newTd2AHref.value = 'javascript:;'
                newTd2A.setAttributeNode(newTd2AHref);

                let newTd2AClass = document.createAttribute('class');
                newTd2AClass.value = 'add_btn';
                newTd2A.setAttributeNode(newTd2AClass);

                //a태그 (minus버튼) 내에 이미지 추가
                let newTd2AHrefImg = document.createElement('img');
                    //이미지의 경로 설정
                    let newTd2AHrefImgSrc = document.createAttribute('src');
                    newTd2AHrefImgSrc.value = 'img/minus_ico.png';
                newTd2AHrefImg.setAttributeNode(newTd2AHrefImgSrc);

                //a태그 (minus버튼) 에 이벤트리스너 추가
                newTd2A.addEventListener('click', removeProduct);
            newTd2A.appendChild(newTd2AHrefImg);

            newTd2.appendChild(newTd2A);
        newTr.appendChild(newTd2);

    // table에 tr추가
    let table = document.querySelector('tbody');
    table.appendChild(newTr);
}