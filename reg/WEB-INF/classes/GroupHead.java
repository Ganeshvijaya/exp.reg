import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class GroupHead extends Member
{
   	int Head_id,Head_grpID,Head_status;
   	
    public boolean addHead(HttpServletRequest request,Connection con) throws SQLException
    {
         Group g =new Group();
         Member m= new Member();
         m.setUtype(request.getParameter("Utype"));
         String Grpname=request.getParameter("UGname");
         System.out.println(Grpname);
           g.createGroup(Grpname,con);
    	   m.addMember(request,con);
    	   Head_id=m.getMember_id(con);
    	   Head_grpID=g.getGroup_id(Grpname, con);
           Head_status=0;    	   
           boolean validate=validateReg(con);
       	   return validate;
    }
    
   
    private  boolean validateReg(Connection con) throws SQLException//method to register 
	{   
		     int k=0;
			PreparedStatement pst1=getStatement(con);
			k=pst1.executeUpdate();
			if(k==1)
			{ 
				System.out.println("done"); return true; 
			}
				else
				 return false;
		
		
	}
	
	private PreparedStatement getStatement(Connection con) throws SQLException//method to prepare a statement
	{  
		PreparedStatement pst1=con.prepareStatement("insert into Heads (Head_id,Head_grpID,Head_status) values(?,?,?)");
		//pst1.setInt(1,Mem_id); made member id autoincrement
		pst1.setInt(1,Head_id);
		pst1.setInt(2, Head_grpID );
		pst1.setInt(3, Head_status);
		return pst1;
	}
}