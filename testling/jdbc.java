import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql
public class test1 {

	private Connection con;
	public jdbc() throws ClassNotFoundException, SQLException {

		

	}
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		con.close();
	}
	
	public void getInfected()
	{
		String sql = "Select cases from Corona where country = 'Austra' ";
		Statement stmt  = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int infected = rs.getInt("infected");
		System.out.println(infected + " sind in österreich infiziert");
		
		
	}
}
