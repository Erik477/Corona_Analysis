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

	Class.forName("com.mysql.jdbc.Driver");
	 con = DriverManager.getConnection(
			"jdbc:mysql://localhost/Corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "12345");

}

public ArrayList<String> getCountry() throws SQLException {

	ArrayList<String> countr = new ArrayList<>();
	PreparedStatement stmt;
	ResultSet rs;

	String Name;
	String format;
	String confirmedCases;
	int confirmed = 0;
	String sql1 = "select cases  from Corona limit 187";
	stmt = con.prepareStatement(sql1);
	rs = stmt.executeQuery();
	while(rs.next())
	{
		confirmed = confirmed + rs.getInt("cases");
	}
	Name = "World";
	confirmedCases = Integer.toString(confirmed);
	format = String.format("%-30s \t %s", Name, confirmedCases);
	String sql = "select country, cases from Corona limit 187;";
	countr.add(format);
	stmt = con.prepareStatement(sql);

	// stmt.setString(1,ss);
	rs = stmt.executeQuery();
	while (rs.next()) {
		Name = rs.getString("Country");
		confirmed = rs.getInt("cases");
		confirmedCases = String.valueOf(confirmed);
		format = String.format("%-30s \t %s", Name, confirmedCases);
		countr.add(format);
	}

	stmt.close();
	rs.close();
	// return countr;
	System.out.println(countr);
	return countr;

}


public void close() throws SQLException {
	// TODO Auto-generated method stub
	con.close();
}
}
