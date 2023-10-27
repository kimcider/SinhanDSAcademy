package _12_05_Annotation;

import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) throws Exception{
		Method[] declaredMethods = Service.class.getDeclaredMethods();
		for(Method method : declaredMethods) {
			//PrintAnnotation얻기
			PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);
			printLine(printAnnotation);
			method.invoke(new Service());
			printLine(printAnnotation);
		}
	}
	
	public static void printLine(PrintAnnotation printAnnotation) {
		if(printAnnotation != null) {
			int number = printAnnotation.number();
			String value = printAnnotation.value();
			for(int i = 0; i < number; i++) {
				System.out.print(value);
			}
			System.out.println();
		}
	}
}