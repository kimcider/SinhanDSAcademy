package chapter09;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"chapter09"})
@EnableWebMvc
//인터페이스를 스캔해주는 설정
@MapperScan(basePackages = {"chapter09"}, annotationClass = Mapper.class)
@EnableTransactionManagement
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
		
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		//로그를 보기위해 드라이버 변경
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("testuser");
		dataSource.setPassword("test1234");
		return dataSource;
	}
	// Mybatis 빈 만들기/
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
		dtm.setDataSource(dataSource());
		return dtm;
	}
}
