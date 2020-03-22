package testling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc {

	private Connection con;
	public jdbc() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(
				"jdbc:mysql://localhost/Corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "12345");

	}
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		con.close();
	}
	
	public void getInfected() throws SQLException
	{
		String sql = "Select cases from Corona where country = 'Austria' ";
		Statement stmt  = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int infected = rs.getInt("cases");
		System.out.println(infected + " sind in österreich infiziert");
		
		rs.close();
		stmt.close();
	}
}
