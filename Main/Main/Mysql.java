package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Mysql{
	
private Connection con;

public Mysql() throws ClassNotFoundException, SQLException {

	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(
			"jdbc:mysql://192.168.0.176:3306/Corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "3r!k");
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
	
	format = String.format("%-7d \t %s", confirmed, Name);
	String sql = "select country, cases from Corona limit 187;";
	countr.add(format);
	stmt = con.prepareStatement(sql);

	// stmt.setString(1,ss);
	rs = stmt.executeQuery();
	while (rs.next()) {
		Name = rs.getString("Country");
		confirmed = rs.getInt("cases");
		if(confirmed < 10)
		{
			format = String.format("%-6d     \t %-30s", confirmed, Name);
		}else {
		format = String.format("%-6d \t %-30s", confirmed, Name);
		}
		countr.add(format);
	}

	stmt.close();
	rs.close();
	// return countr;
	System.out.println(countr);
	return countr;

}

public String getDate() {
	long date = 1587307063;		
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		
	String convertedDate = sdf.format(new java.util.Date (date*1000));
	return convertedDate;
}

public void close() throws SQLException {
	// TODO Auto-generated method stub
	con.close();
}
}
