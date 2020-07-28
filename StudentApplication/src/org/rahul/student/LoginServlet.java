package org.rahul.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection connection;
	public Statement statement;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		try {
			System.out.println(request.toString());
			String uname = request.getParameter("uname");
			String psw = request.getParameter("psw");
		System.out.print("hi");
		Class.forName("com.mysql.jdbc.Driver");
		
		connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root"); 
		
		if (connection != null) {
			statement =  connection.createStatement();
			
			//String sql = "INSERT INTO student (sno, sname, DOB, Phonenumber,gender,language,department, Address) VALUES ("+sno+",'"+sname+"','"+dob+"','"+phonenumber+"','"+gender+"','"+langs+"','"+dept+"','"+address+"')";
			//String sql = "delete from student where sno = " + sno;
			//String sql = "update student set sname = '" + sname + "' where sno = " + sno;
			String sql = "SELECT * FROM user where username = '"+ uname +"' and password = '" + psw + "'";
			System.out.println(sql);
			ResultSet rs=statement.executeQuery(sql);
			boolean status = rs.next(); 
			
			if(status) {
				
				HttpSession oldSession = request.getSession(false);
	            if (oldSession != null) {
	                oldSession.invalidate();
	            }
	            //generate a new session
	            HttpSession newSession = request.getSession();
	            System.out.print("rahul"+newSession);
				
				Cookie ck=new Cookie("user",uname);//creating cookie object  
				response.addCookie(ck);//adding cookie in the response  
				RequestDispatcher rd=request.getRequestDispatcher("index.html");  
		        rd.forward(request,response);
			} else {
				writer.print("Sorry username or password error");  
		        RequestDispatcher rd=request.getRequestDispatcher("login.html");  
		        rd.include(request,response);
			}
			
			writer.println("Data saved successfully");
			writer.close();
		} else {
			System.out.println("Connection null");
		}
	
	
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
