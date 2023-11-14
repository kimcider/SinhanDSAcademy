package kr.co.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//여기 Test클래스가 있기 때문에 src/test/java에 클래스를 Test로만들면안된다. 
import org.junit.Test;

public class TestEx {
	@BeforeClass
	public static void beforeClass() {
		System.out.println("처음 한번만");
	}
	@AfterClass
	public static void afterClass() {
		System.out.println("마지막 한번만");
	}
	
	@Before
	public void before() {
		System.out.println("before");
	}
//	@Before
	public void before2() {
		System.out.println("before2");
	}
	
	@After
	public void After() {
		System.out.println("after");
	}
	
	//메인메소드 필요 없고 메소드 위에다가 annotation을쓰면된다.
	@Test
	public void test() {
		System.out.println("테스트");
	}
	@Test
	public void test2() {
		System.out.println("테스트2");
	}
	
	
	public int temp() {
		return 5;
	}
	@Test
	public void tempTest() {
		System.out.println(temp());
	}
	
}
