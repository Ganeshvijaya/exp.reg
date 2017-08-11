import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
public class GetGrpProcess extends HttpServlet
{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		   	
			    PrintWriter out =response.getWriter();
			    Connection con=ConnectDB.getConnection();
			    try
			    {
				    
				    
					PreparedStatement pst=con.prepareStatement("select Group_name from Groups");
					ResultSet rs=pst.executeQuery();
					 JSONObject obj =new JSONObject();
				     int i=0;
					 while(rs.next())
					 { obj.put(i,rs.getString("Group_name")); i++; };
					 out.print(obj);
					 con.close();
			    }
			    catch(SQLException ex)
			    {
			    	ex.printStackTrace();
			    }
			    
			    
		}
}