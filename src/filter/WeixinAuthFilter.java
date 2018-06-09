package filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WeixinFinalValue;




public class WeixinAuthFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {  
      
    }

	@Override
	public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest=(HttpServletRequest) request;
		HttpServletResponse hResponse=(HttpServletResponse) response;
		String method=hRequest.getRequestURI();
		String scope="snsapi_base";
		if(method.indexOf("/wget")>0) {
			chain.doFilter(hRequest, hResponse);
			return;
		}else {
		    String agent=hRequest.getHeader("User-Agent");
			if(agent!=null) {
				String code=request.getParameter("code");
				String state=request.getParameter("state");
				if(code!=null) {
					chain.doFilter(hRequest, hResponse);
					return;
				}else {
					String path=hRequest.getRequestURL().toString();
					String query=hRequest.getQueryString();
					if(query!=null) {
						path=path+"?"+query;
				    }
					if(method.indexOf("/Register")>0) {
						scope="snsapi_userinfo";
					}
					 String url=WeixinFinalValue.AUTH_URL;
					    url=url.replaceAll("APPID", WeixinFinalValue.APPID)
					   .replace("REDIRECT_URI",URLEncoder.encode(path, "utf-8"))
					   .replace("STATE", "1")
					   .replace("SCOPE",scope);
					    System.out.println("链接地址为"+url);
					    hResponse.sendRedirect(url);
					    return;
				}

			}
		}
		chain.doFilter(hRequest, hResponse);
	}
	
	 public void destroy() {  
         
	    }  
	
}
