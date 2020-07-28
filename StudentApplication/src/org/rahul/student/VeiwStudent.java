package org.rahul.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VeiwStudent extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public Connection connection;
	public Statement statement;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		response.setContentType("text/html");
		//response.setContentType("application/vnd.ms-excel");
		//response.setHeader("Content-disposition","attachment;filename=Report.xls");
		PrintWriter writer = response.getWriter();
		Cookie ck[]=request.getCookies();  
		for(int i=0;i<ck.length;i++){  
		 System.out.print("<br>"+ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
		writer.println("<html>"); 
		writer.println("<head>");
		writer.println("<meta charset='"+"UTF-8 "+"'>");
		writer.println(" <meta name='"+"veiwpoint' content='"+"width=device-width,initial-scale=1,shrink-to-fit=no"+"'/>");
		writer.println("<link rel='"+"stylesheet' href='"+"css/bootstrap.min.css"+"'/>");
		writer.println("</head>");
		writer.println("<body>");
		System.out.println("hi");
		try
		{
			
	Class.forName("com.mysql.jdbc.Driver");
			
			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root"); 
			
			if (connection != null) {
				statement =  connection.createStatement();
				
				//String sql = "INSERT INTO student (sno, sname, DOB, Phonenumber , Address) VALUES ("+sno+",'"+sname+"','"+dob+"','"+phonenumber+"','"+address+"')";
				//String sql = "delete from student where sno = " + sno;
				//String sql = "update student set sname = '" + sname + "',DOB='"+dob+"',Phonenumber='"+phonenumber+"',Address='"+address+"' where sno = " + sno;
				ResultSet rs=statement.executeQuery("SELECT * FROM studentdb.student;");
			if(rs.next()==true)
					{	
					rs.previous();
							writer.println("<table class='"+"table "+"'> <thead class='"+" thead-dark "+"'>");
							writer.println("<tr><th scope='"+"col'>sno</th><th scope='"+"col'>name</th><th scope='"+"col'>DOB</th><th scope='"+"col'>phonenumber</th><th scope='"+"col'>gender</th><th scope='"+"col'>depatment</th><th scope='"+"col'>languages</th><th scope='"+"col'>address</th></tr>");
							writer.println("</thead>"); 
							writer.println("<tbody>"); 
							
						while (rs.next())
						{
							int a = rs.getInt("sno"); 
							String b = rs.getString("sname");
							Date c=rs.getDate("DOB");
							long d=rs.getLong("Phonenumber");
							String e=rs.getString("Address");
							String f = rs.getString("gender");
							String g =rs.getString("department");
							String h =rs.getString("language");
							writer.println("<tr><th scope='"+"row "+"'>" + a + "</td><td>" + b + "</td><td>" + c + "</td><td>" + d + "</td><td>" + f + "</td><td>"+ g +"</td><td>"+ h +"</td><td>"+ e +"</td></tr>");
							
						}
						writer.println("</tbody>"); 
						writer.println("</table>"); 
						
						writer.println("</html></body>"); 
						writer.println("Data saved successfully");
						writer.close();
					}
			else
			{
				writer.print("no student detail information is found ");
			}
				}
		}
		catch(SQLException e) {
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