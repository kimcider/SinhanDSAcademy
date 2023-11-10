package chapter06.exer;

public class CalculatorImpl implements Calculator {

	//재귀호출 사용 x version
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		
		long result = 1;
		for(int i = 0; i <=num; i++) {
			result *= i;
		}
		
		long end = System.nanoTime();
		
		System.out.println("for문을 돌렸을 떄 실행시간: " + (end - start));
		
		return result;
	}

}
