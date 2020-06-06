package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * Hier wird die Msql connection erstellt und die daten geholt.
 * @author Erik
 * 
 */







public class Mysql{
	
private Connection con;

public Mysql() throws ClassNotFoundException, SQLException {

	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection("jdbc:mysql://localhost/coronedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "admin");

}

public ArrayList<String> getCountrydata() throws SQLException {
	
	ArrayList<String> Data = new ArrayList<>();
	PreparedStatement stmt;
	ResultSet rs;
	
	String Country;
	String Region;
	int Population;
	int Area;
	int PopDensity;
	int Coastline;
	int Migration;
	int InfantMortality;
	int GDP;
	int Literacy;
	int confirmed = 0;
	
	String sql1 = "select * from countrydata";
	stmt = con.prepareStatement(sql1);
	rs = stmt.executeQuery();
	while(rs.next())
	{
		confirmed = confirmed + rs.getInt("cases");
	}
	

	stmt.close();
	rs.close();
	// return countr;
	System.out.println(Data);
	
	return Data;
	
}

public ArrayList<String> getCountry() throws SQLException {

	ArrayList<String> countr = new ArrayList<>();
	PreparedStatement stmt;
	ResultSet rs;

	String Name;
	String format;
	String confirmedCases;
	int confirmed = 0;
	String sql1 = "select * from coronadata order by epoch desc LIMIT 187";
	stmt = con.prepareStatement(sql1);
	rs = stmt.executeQuery();
	while(rs.next())
	{
		confirmed = confirmed + rs.getInt("cases");
	}
	Name = "World";
	
	format = String.format("%-7d \t %s", confirmed, Name);
	String sql = "select country, cases from coronadata limit 187;";
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

public ArrayList<Long> getDate() throws SQLException{
	
	ArrayList<Long> date = new ArrayList<Long>();
	String sql = "select * from coronadata where country = 'Austria' order by epoch;";
	PreparedStatement stmt = con.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();
	
	long epoch;
	while(rs.next())
	{
		epoch = rs.getLong("epoch");
		date.add(epoch);
	}
	stmt.close();
	rs.close();
	return date;
}

public ArrayList<Integer> getInfo(String input, String output) throws SQLException{
	
	ArrayList<Integer> cases = new ArrayList<Integer>();
	String sql = "select * from coronadata where country = '"+ output +"' order by epoch;";
	PreparedStatement stmt = con.prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();
	
	int info = 0;
	while(rs.next())
	{
		info = rs.getInt(input.toLowerCase());
		cases.add(info);
	}
	stmt.close();
	rs.close();
	return cases;
}



public void close() throws SQLException {
	
	con.close();
}

}