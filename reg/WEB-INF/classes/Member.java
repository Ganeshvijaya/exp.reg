import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;

public class Member
{
	int Mem_id,Mem_grpID,Mem_status;
	String Mem_name,Mem_pass,Mem_email,Mem_mobile,Mem_Dob;
    String Utype=null;

    public boolean addMember(HttpServletRequest request,Connection con)throws SQLException
    {
    	getParmas(request,con);
    	boolean validate=validateReg(con);
    	return validate;
    }
    private void getParmas(HttpServletRequest request,Connection con) throws SQLException// method to get paramters and intilise the attributes
	{
		// Mem_id=Integer.parseInt(request.getParameter("Mid"));
		 String Mem_grpName=null;
		 if(Utype.equals("Member"))
				 Mem_grpName=request.getParameter("UGroup");
		 else
			 Mem_grpName=request.getParameter("UGname");
		 Mem_grpID=new Group().getGroup_id(Mem_grpName,con);
		 Mem_status=0;
		 Mem_name =request.getParameter("UFname")+" "+request.getParameter("ULname");
		 Mem_pass=request.getParameter("Upass");
		 Mem_email=request.getParameter("Umail");
		 Mem_mobile=request.getParameter("Uno");
		 Mem_Dob=request.getParameter("Udob");
		 System.out.println(Mem_Dob);
		 Date date;
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		 try {
		      date=dateFormat1.parse(Mem_Dob);
		      //System.out.println(date);
		     Mem_Dob = dateFormat.format(date);
		     System.out.println(Mem_Dob);
		 } catch (ParseException e) {
		     e.printStackTrace();
		 }
		 
	}
	
    private  boolean validateReg(Connection con) throws SQLException//method to register 
	{   
		     int k=0;
			PreparedStatement pst1=getStatement(con);
			k=pst1.executeUpdate();
			if(k==1)
			{ System.out.println("done"); return true; 
			}
				else
				 return false;
		
		
	}
	
	private PreparedStatement getStatement(Connection con) throws SQLException//method to prepare a statement
	{  
		PreparedStatement pst1=con.prepareStatement("insert into members (Mem_name,Mem_Dob,Mem_email,Mem_pass,Mem_mobile,Mem_grpID,Mem_status) values(?,?,?,?,?,?,?)");
		//pst1.setInt(1,Mem_id); made member id autoincrement
		pst1.setString(1,Mem_name);
		pst1.setString(2, Mem_Dob );
		pst1.setString(3, Mem_email);
		pst1.setString(4, Mem_pass);
		pst1.setString(5, Mem_mobile);
		pst1.setInt(6,Mem_grpID);
		pst1.setInt(7,Mem_status);
		return pst1;
	}
	
	public int getMember_id(Connection con) throws SQLException
	{
		PreparedStatement pst=con.prepareStatement("select Mem_id from Members where Mem_email=?");
		pst.setString(1,Mem_email);
		ResultSet rs=pst.executeQuery();
		rs.next();
		Mem_id=rs.getInt("Mem_id");
		return Mem_id;
	}
	public void setUtype(String Utype) {
        this.Utype = Utype;
    }
}