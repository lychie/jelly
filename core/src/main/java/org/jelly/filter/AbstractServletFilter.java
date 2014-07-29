package org.jelly.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.jelly.util.StringUtil;
/**
 * Abstract Servlet Filter
 * @author Lychie Fan
 * @since 1.3.0
 */
public abstract class AbstractServletFilter implements Filter {

	@Override
	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String uri = httpServletRequest.getServletPath();
		if(matches(StringUtil.substringAfterLast(uri, "/"))){
			RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(uri);
			dispatcher.forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {  }
	
	/**
	 * <des> 若 uri 符合 servlet 规则, 交给 servlet 容器处理 </des>
	 * @since 1.3.0
	 */
	protected abstract boolean matches(String uri);

	@Override
	public abstract void init(FilterConfig config);
	
}