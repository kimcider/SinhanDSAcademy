package chapter04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		BoardController con = 
				ctx.getBean("boardController", BoardController.class);
		
		con.list();
	}

}
