package org.rahul.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStudent extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public Connection connection;
	public Statement statement;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("hi");
		Cookie ck[]=request.getCookies();  
		for(int i=0;i<ck.length;i++){  
		 System.out.print("<br>"+ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
		try{	
			String sno = request.getParameter("sno");
			/*String sname = request.getParameter("sname");
			String dob = request.getParameter("dob");
			String phonenumber = request.getParameter("phn");
			String address = request.getParameter("address");*/
			
			PrintWriter writer = response.getWriter();
			 
			System.out.println(sno);
			//writer.println(sno + " : " + sname + " : " + dob + " : " + phonenumber + " : " + address);
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root"); 
			
			if (connection != null) {
				statement =  connection.createStatement();
				
				//String sql = "INSERT INTO student (sno, sname, DOB, Phonenumber , Address) VALUES ("+sno+",'"+sname+"','"+dob+"','"+phonenumber+"','"+address+"')";
				String sql = "delete from student where sno = " + sno;
				//String sql = "update student set sname = '" + sname + "' where sno = " + sno;
				statement.executeUpdate(sql);
				
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
}