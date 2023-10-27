package _02_filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;


public class CharacterEncodingFilter extends HttpFilter implements Filter {
	String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		//web.xml에서 name이 encoding인 파라미터를 가져온다. (value = "UTF-8") 
		encoding = fConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);

		// 여기가 가장 중요하다!
		// chain.doFilter()메소드를 거쳐서 우리가 실행할 서블릿에 가서 이 전까지 request에 수행한 작업을 해당 서블릿에 전달한다.
		// 따라서 당연히 setCharEncoding()은 이 메소드 전에 해야한다. 
		chain.doFilter(request, response);
		System.out.println(encoding + "으로 인코딩중...");
	}
}
