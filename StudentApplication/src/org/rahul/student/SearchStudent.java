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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


	public class SearchStudent extends HttpServlet{
		private static final long serialVersionUID = 1L;
		public Connection connection;
		public Statement statement;
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//super.doGet(req, resp);
			PrintWriter writer = response.getWriter();
	
//			System.out.println("hi " + request.getSession(false).getId());
			HttpSession session = request.getSession(false);
	        if(session != null) {
		
			try
			{
				String sno = request.getParameter("sno");
				String sname = request.getParameter("sname");
				String dob = request.getParameter("dob");
				String phonenumber = request.getParameter("phn");
				String address = request.getParameter("address");
				
				Class.forName("com.mysql.jdbc.Driver");
				
				connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root"); 
				
				if (connection != null) {
					statement =  connection.createStatement();
					String sql = "SELECT * FROM studentdb.student where sno="+sno;
					ResultSet rs = statement.executeQuery(sql);
					
					if(rs.next()==true) {
						rs.previous();
						while (rs.next())
						{
							int a = rs.getInt("sno"); 
							String b = rs.getString("sname");
							Date c=rs.getDate("DOB");
							long d=rs.getLong("Phonenumber");
							String e=rs.getString("Address").trim();
							String f=rs.getString("gender");
							String g=rs.getString("department");
							String h=rs.getString("language");
							response.setContentType("application/json");
							
	//						System.out.println(response.)
							String res = "{\"sno\" :\""+a+"\",\"sname\" :\""+b+"\", \"DOB\" : \""+c+"\", \"Phonenumber\":\""+d+"\", \"Address\":\""+e+"\",\"gender\":\""+f+"\",\"department\":\""+g+"\",\"language\":\""+h+"\"}";
							writer.print(res);
						}
					}
					else {
						writer.print("No Student found with entered sno.");
					} 
//				writer.println("Data saved successfully");
				writer.close();
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