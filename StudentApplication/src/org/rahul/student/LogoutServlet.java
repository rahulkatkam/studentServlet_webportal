package org.rahul.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		 response.setContentType("text/html");  
		 String uname = request.getParameter("uname");
         
         HttpSession session = request.getSession(false);
         if(session != null)
         {
        	 System.out.println("still session is there");
        	 session.removeAttribute("JSESSIONID");
//        	 session.removeAttribute("username");
        	 session.invalidate();
         }
         
//         Cookie ck=new Cookie("user",uname);  
         Cookie[] cookies = request.getCookies();
         List<Cookie> cookieList = new ArrayList<Cookie>();
         cookieList = Arrays.asList(cookies);
         for(Cookie cookie:cookieList) {
        	 cookie.setValue(null);
        	 cookie.setMaxAge(0);
//        	 cookie.setPath(theSamePathAsYouUsedBeforeIfAny);
        	 response.addCookie(cookie);
        	 
         }
//         cookies.setValue(null);
//         ck.setMaxAge(0);  
//         response.addCookie(ck);  
           
         response.sendRedirect(request.getContextPath() + "/login.html");
         
         System.out.print("you are successfully logged out!");  
	}

}
