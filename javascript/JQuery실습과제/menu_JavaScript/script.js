let liArray = document.querySelectorAll('.head_bot > .size > .gnb > .depth1 > li');
for(let i = 0; i < liArray.length; i++){
    console.log(liArray[i]);
    liArray[i].addEventListener('mouseover', slideDown);
    liArray[i].addEventListener('mouseout', slideUp);
}

function slideDown(){
    let temp = this.querySelector('.depth2');
    temp.style.display = 'block';
}

function slideUp(){
    let temp = this.querySelector('.depth2');
    temp.style.display = 'none';
}