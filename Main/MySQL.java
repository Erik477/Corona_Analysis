package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQL {

	private Country country;
	private Connection con;
	public MySQL() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(
				"jdbc:mysql://localhost/Corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "12345");

	}
	
	public ArrayList<String> getName() throws SQLException
	{
		ArrayList<String > names = new ArrayList<String>();
		String sql = "Select country from Corona ";
		Statement stmt  = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String name = "";
		while(rs.next())
		{
			name = rs.getString("country");
			names.add(name);
		}
		return names;
	}
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		con.close();
	}
}
