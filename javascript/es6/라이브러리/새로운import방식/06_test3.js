//test1, test2를 불러오는 파일.



import { func1 } from './06_test1.js';
import { func2, func4 } from './06_test2.js';


console.log('test3.js파일 내의 실행문');

export{func1, func2};

//기본으로 내보내기
export default func4;