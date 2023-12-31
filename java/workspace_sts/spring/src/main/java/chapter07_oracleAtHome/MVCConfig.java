package chapter07_oracleAtHome;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"chapter07_oracleAtHome"})
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer{
	
//	//아래 코드를 넣어주지 않으면 화면에 나오지는 않는다. 접속을 할 때 맵핑된 메소드에 정의해둔 sysout프린트만 되면 된다.
//	//그다음에 prefix, suffix를 설정해줘야한다 그렇지 않으면 jsp로 맵핑되지 않는다 ㅎㅎ. 
//	//webMvcConfiguer들어가면 아래 메소드가있따.
//	//이 메소드에서 prefix, suffix규정을하는것. 아마 서블릿xml에도 configureViewResolvers가 있을 것이다.
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

	//HikariCP
		//얘는 우리가 만든 클래스가 아니기떄문에 @Bean을 써야 객체가 생성이 된다.
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		//드라이버 바꿔넣기.
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		//url바꿔넣으면 된다.
		dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		dataSource.setJdbcUrl("jdbc:oracle:thin:@10.211.55.5:1521:xe");
		//들어갈 유저이름
		dataSource.setUsername("testuser");
		//유저의 비밀번호. 
		dataSource.setPassword("test1234");
		return dataSource;
	}
	// Mybatis 빈 만들기/
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();

		/* DataSource Bean 주입 */
		ssf.setDataSource(dataSource());
		
		/* SQL문을 담아 둘 xml파일의 경로 설정 */
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		//classpath로해야 다른 프로젝트에서도 쓰기 편함
		ssf.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml")); 
		return ssf.getObject();
	}
	
	//DAO에서 주입받을 객체(빈)을 등록
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
