package kr.co.project;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

import util.LoginInterceptor;

@Configuration
@ComponentScan(basePackages = {"kr.co.project"})
@EnableWebMvc
@MapperScan(basePackages = {"kr.co.project"}, annotationClass = Mapper.class)
@EnableTransactionManagement
public class MVCConfig implements WebMvcConfigurer{
	//DB접속정보
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.userpassword}")
	private String userpassword;
	
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/event/index.do");
	}

//	@Bean(destroyMethod = "close")
//	public HikariDataSource dataSource() {
//		HikariDataSource dataSource = new HikariDataSource();
//		
//		dataSource.setDriverClassName(driver);
//		dataSource.setJdbcUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(userpassword);
//		return dataSource;
//	}

//	@Bean
//	public SqlSessionFactory sqlSessionFactory() throws Exception{
//		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
//		ssf.setDataSource(dataSource());
//		return ssf.getObject();
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		DataSourceTransactionManager dtm = new DataSourceTransactionManager();
//		dtm.setDataSource(dataSource());
//		return dtm;
//	}
	
	//파일 업로드
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1024 * 1024 * 5); // 5Mb
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
	
	//인터셉터 등록
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// interceptor를 적용할 url을 설정하면된다.
		
		//빈 주입도 해주고 url설정
		registry.addInterceptor(loginInterceptor())
			.addPathPatterns("/member/regist.do")
			.addPathPatterns("/member/list.do");
	}
	
	
	//property 설정
	@Bean
	public static PropertySourcesPlaceholderConfigurer property() {
		PropertySourcesPlaceholderConfigurer property = new PropertySourcesPlaceholderConfigurer();
		//우리가 db.properties를 resources바로아래에담았기때문에 ㅇㅇ. 
		property.setLocations(new ClassPathResource("db.properties"));
		return property;
	}
}
