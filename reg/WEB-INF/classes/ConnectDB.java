import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB 
{
	public static Connection getConnection()
	{
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker","root","1995");
			System.out.println("Connected.......");
			}
			catch(Exception op){op.printStackTrace();}
		return con;
	}

}
