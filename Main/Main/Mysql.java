package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Mysql{
	
private Connection con;

public Mysql() throws ClassNotFoundException, SQLException {

	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(
			 "jdbc:mysql://localhost/Corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "12345");
	 
}
public ArrayList<String> getCountry() throws SQLException{
	
	ArrayList<String> countr = new ArrayList<>();
	
	String sql = "select country, cases from Corona limit 200;";
	
	 PreparedStatement stmt = con.prepareStatement(sql);
	 
	 //stmt.setString(1,ss);
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
		{
			String Name = rs.getString("Country");
			int confirmed = rs.getInt("cases");
			String confirmedCases = String.valueOf(confirmed);
			String format = String.format("%-30s \t %s",Name,confirmedCases);
			countr.add(format);
		}
		stmt.close();
		rs.close();
		//return countr;
		System.out.println(countr);
		return countr;
	
}


public void close() throws SQLException {
	// TODO Auto-generated method stub
	con.close();
}
}
