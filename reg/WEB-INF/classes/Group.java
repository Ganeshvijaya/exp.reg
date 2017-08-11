import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Group
{
	int Group_id;
	String Group_name;
	

	
	public int getGroup_id(String name,Connection con) throws SQLException
	{
		
	     PreparedStatement pst=con.prepareStatement("Select Group_id from Groups where Group_name=?");
	     pst.setString(1,name);
	     ResultSet rs=pst.executeQuery();
	     rs.next();
	     Group_id =rs.getInt("Group_ID");
         return Group_id;
         
    }

    public void createGroup(String name,Connection con) throws SQLException
    {
    	 System.out.println(name);
    	 PreparedStatement pst=con.prepareStatement("insert into Groups (Group_name) values(?)");
    	 pst.setString(1,name);
    	 pst.executeUpdate();
    }


}