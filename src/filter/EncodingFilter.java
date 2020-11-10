package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */

@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {
	private String encoding; // 定义变量接收初始化的值
	public EncodingFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		this.encoding = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (encoding == null) {
			encoding = "UTF-8";
		}

		// 设置字符编码链锁
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("执行了dofilter()");
		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 接收web.xml配置文件中的初始参数
				encoding = fConfig.getInitParameter("encoding");

	}

}
