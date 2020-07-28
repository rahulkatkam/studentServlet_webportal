package org.rahul.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/update.html")
public class AuthenticationFilter implements Filter{

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext();
		System.out.println("AuthenticationFilter initialized");
	}

//	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fChain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        
        Cookie ck[]=req.getCookies();  
//        System.out.println("cookie"+ck.length);
        
        if(ck == null)
        {
        	System.out.println("Unauthorized access request");
            res.sendRedirect(req.getContextPath() + "/login.html");
        }
        else
        {
            // pass the request along the filter chain
        	System.out.println("In fileter redirection.");
            fChain.doFilter(request, response);
        }

		/*
		 * String uri = req.getRequestURI();
		 * 
		 * HttpSession session = req.getSession(false);
		 * System.out.println("Requested Resource::"+uri);
		 * 
		 * if (session == null && uri.contains("Student")) { //checking whether the
		 * session exists System.out.println("Unauthorized access request");
		 * res.sendRedirect(req.getContextPath() + "/login.html"); } else { // pass the
		 * request along the filter chain System.out.println("In fileter redirection.");
		 * fChain.doFilter(request, response); }
		 */
	} 
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
