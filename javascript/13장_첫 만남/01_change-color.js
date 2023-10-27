var heading = document.querySelector('#heading');

console.log(heading);

heading.onclick = function () {
    console.log('안녕하세요');
    color = 'red';
    heading.style.color = color;
}