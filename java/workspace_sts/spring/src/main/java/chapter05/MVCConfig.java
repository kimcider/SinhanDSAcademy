package chapter05;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"chapter05"})
@EnableWebMvc  //이 설정을 반드시 넣어놔야함. 이거를 키고 해야 jsp와 맵핑이 된다.
public class MVCConfig implements WebMvcConfigurer{
	
	//아래 코드를 넣어주지 않으면 화면에 나오지는 않는다. 접속을 할 때 맵핑된 메소드에 정의해둔 sysout프린트만 되면 된다.
	//그다음에 prefix, suffix를 설정해줘야한다 그렇지 않으면 jsp로 맵핑되지 않는다 ㅎㅎ. 
	//webMvcConfiguer들어가면 아래 메소드가있따.
	//이 메소드에서 prefix, suffix규정을하는것. 아마 서블릿xml에도 configureViewResolvers가 있을 것이다.
	public void configureViewResolvers(ViewResolverRegistry registry) {
		//이렇게 해주면 비로소 맵핑이 될 것이다.
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	
	
	//static 요소들을 정적으로 설정할 수 있도록 해주는것
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();//본래는 disable로되어있다. 
	}
	
	
	//정적 페이지 설정
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/event/index.do");
	}

}
