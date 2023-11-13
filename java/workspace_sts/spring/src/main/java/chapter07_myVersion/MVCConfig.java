package chapter07_myVersion;

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
@ComponentScan(basePackages = {"chapter07_myVersion"})
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer{
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();//본래는 disable로되어있다. 
	}
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/event/index.do");
	}

	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("testuser");
		dataSource.setPassword("test1234");
		return dataSource;
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();

		ssf.setDataSource(dataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssf.setMapperLocations(resolver.getResources("classpath:/mapper_my/**/*.xml")); 
		return ssf.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
