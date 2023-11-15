package chapter10_utilTest;

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
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"chapter10_utilTest"})
@EnableWebMvc
@MapperScan(basePackages = {"chapter10_utilTest"}, annotationClass = Mapper.class)
@EnableTransactionManagement
public class MVCConfig implements WebMvcConfigurer{
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/event/index.do");
	}

	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		dataSource.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("testuser");
		dataSource.setPassword("test1234");
		return dataSource;
	}

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
	
	//파일 업로드
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1024 * 1024 * 5); // 5Mb
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
}
