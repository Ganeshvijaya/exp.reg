import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.org.apache.xml.internal.serialize.Printer;

public class RegProcess extends HttpServlet 
{
	 String Utype;   

	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		   	
		      Connection con =ConnectDB.getConnection();
		       Utype=request.getParameter("Utype");
		       System.out.println(Utype);
		      if(Utype.equals("Member"))
		      {  System.out.println("in member");
		    	 registerMember(request, response,con); 
		      }
		    	  
		      if(Utype.equals("Head"))
		      {   
		    	  registerHead(request, response,con); 
		      }
            
		}

		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		              doGet(request, response);	//to handle post request
		}
	    
		private void registerMember(HttpServletRequest request, HttpServletResponse response,Connection con) throws IOException
		{
		           Member m=new Member();
		           m.setUtype(Utype);
		           boolean success=false;
		           try
		           {           System.out.println("in add");
		                success=m.addMember(request,con);
		                con.close();
		           }
		           catch(SQLException ex)
		           {
		             ex.printStackTrace();
		           }
		           finally
		           {
		        	   
		        	   if(success)
			        	   response.sendRedirect("reg.html?msg=Sucessfull");
			           else
			        	   response.sendRedirect("reg.html?msg=Wrong");
		           }
		}
		
		
		private void registerHead(HttpServletRequest request, HttpServletResponse response,Connection con) throws IOException
		{
			   GroupHead m=new GroupHead();
	           boolean success=false;
	           try
	           {    System.out.println("in add");
	                success=m.addHead(request,con);
	                con.close();
	           }
	           catch(SQLException ex)
	           {
	             ex.printStackTrace();
	           }
	           finally
	           {
	        	   
	        	   if(success)
		        	   response.sendRedirect("reg.html?msg=Sucessfull");
		           else
		        	   response.sendRedirect("reg.html?msg=Wrong");
	           }     
		}
		 
		
		
		
		
		
		
	}
