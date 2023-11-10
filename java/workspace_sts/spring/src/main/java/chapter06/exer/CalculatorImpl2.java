package chapter06.exer;


/* 얘는 함수 안에 타이머를 넣을 수 있는 방법이 없고 얘를 한번 더 감싸서 객체를 주입받아서 시간을 재야 한다. 
 * 그걸위해 감싼 코드가 Calculator2Exe.java */
public class CalculatorImpl2 implements Calculator {

	//재귀호출 사용 version
	@Override
	public long factorial(long num) {
//		long start = System.nanoTime();

		if(num == 0) return 1;
		else {
			return num * factorial(num - 1);
		}
		//끼양 언리쳐블코드래!! ㅜㅜ
//		long end = System.nanoTime();
//		System.out.println("for문을 돌렸을 떄 실행시간: " + (end - start));
	}

}
