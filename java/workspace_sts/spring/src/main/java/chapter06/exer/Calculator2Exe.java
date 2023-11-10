package chapter06.exer;

public class Calculator2Exe implements Calculator {

	private Calculator cal;
	public Calculator2Exe(Calculator cal) {
		this.cal = cal;
	}
	
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		long r = cal.factorial(num);
		long end = System.nanoTime();
		System.out.println("factorial 실행시간: " + (end - start));
		return r;
	}

}
