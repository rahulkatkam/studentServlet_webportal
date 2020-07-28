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

import org.rahul.util.StudentUtility;

//@WebServlet("/insertStudent") 
public class InsertStudent extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public Connection connection;
	public Statement statement;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		System.out.println("hi ");// + request.getSession().getId());
		
		Cookie ck[]=request.getCookies();  
		for(int i=0;i<ck.length;i++){  
		 System.out.print("<br>"+ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
		}  
		try{	
			//System.out.println(request.toString());
			String sno = request.getParameter("sno");
			String sname = request.getParameter("sname");
			String dob = request.getParameter("dob");
			String phonenumber = request.getParameter("phn");
			String address = request.getParameter("address");
			String gender = request.getParameter("gender");
			String dept = request.getParameter("dept");
			String languages[] = request.getParameterValues("language");
			int n=languages.length;
			String langs = "";
			 
			if (languages != null) {
			    System.out.println("Languages are: ");
			    for (int i=0;i<n;i++) {
			        //langs = langs + "" + lang;
			    	if(i==0 )
			    	{
			    		langs+=languages[i];
			    	}
			    	else
			    	{
			    		langs = langs + "," + languages[i];
			    	}
			    		
			    }
			}
			PrintWriter writer = response.getWriter();
			 
			
			System.out.println(sno + " : " + sname + " : " + dob + " : " + phonenumber + " : " + address+":"+langs+":"+gender+":"+dept);
			//writer.println(sno + " : " + sname + " : " + dob + " : " + phonenumber + " : " + address);
			
			StudentUtility utility = new StudentUtility();
			boolean validation = utility.dataValidate(sno,sname,dob,phonenumber);
			if (!validation) {
				writer.print(utility.errorMessage);
			} else {
			
				Class.forName("com.mysql.jdbc.Driver");
				
				connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root"); 
				
				if (connection != null) {
					statement =  connection.createStatement();
					
					String sql = "INSERT INTO student (sno, sname, DOB, Phonenumber,gender,language,department, Address) VALUES ("+sno+",'"+sname+"','"+dob+"','"+phonenumber+"','"+gender+"','"+langs+"','"+dept+"','"+address+"')";
					//String sql = "delete from student where sno = " + sno;
					//String sql = "update student set sname = '" + sname + "' where sno = " + sno;
					statement.executeUpdate(sql);
					
					writer.println("Data saved successfully");
					writer.close();
				} else {
					System.out.println("Connection null");
				}
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