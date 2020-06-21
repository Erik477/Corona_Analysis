package Main;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import javafx.collections.ObservableList;

/**
 * Hier wird die Msql connection erstellt und die daten geholt.
 * 
 * @author Erik
 * 
 */

public class Mysql {

	private Connection con;

	public Mysql() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/coronedata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "admin");


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
	
	String sql1 = "select * from countrydata";
	stmt = con.prepareStatement(sql1);
	rs = stmt.executeQuery();
	while(rs.next())
	{
		Country=rs.getString("Country");
		Region=rs.getString("Region");
		Population=rs.getInt("Population");
		Area=rs.getInt("Area");
		PopDensity=rs.getInt("PopDensity");
		Coastline=rs.getInt("Coastline");
		Migration=rs.getInt("Migration");
		InfantMortality=rs.getInt("InfantMortality");
		GDP=rs.getInt("GDP");
		Literacy=rs.getInt("Literacy");
	}
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
		
		String sql = "select country, cases from coronadata limit 187;";
		
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			Name = rs.getString("Country");
			confirmed = rs.getInt("cases");
			if (confirmed < 10) {
				format = String.format("%-6d     \t %-30s", confirmed, Name);
			} else {
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

	public ArrayList<Long> getDate(String output) throws SQLException {

		ArrayList<Long> date = new ArrayList<Long>();
		String sql = "select * from coronadata where country = '"+ output+ "'  order by epoch;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		long epoch;
		while (rs.next()) {
			epoch = rs.getLong("epoch");
			date.add(epoch);
		}
		stmt.close();
		rs.close();
		return date;
	}

	public ArrayList<Integer> getInfo(String input, String output) throws SQLException {

		ArrayList<Integer> infoList = new ArrayList<Integer>();
		String sql = "select * from coronadata where country = '" + output + "' order by epoch;";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		int info = 0;
		while (rs.next()) {
			info = rs.getInt(input.toLowerCase());
			infoList.add(info);
		}
		stmt.close();
		rs.close();
		return infoList;
	}

	public String convertDate(long epoch) { // konvertieren der epoch zahl in ein gültiges Zeitformat

		Date date = new Date(epoch);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		String formatted = format.format(date);
		format.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));// your zone
		formatted = format.format(date);
		System.out.println(formatted);
		return formatted;
	}
	
	public int totalInfo(String textFromLabel) throws SQLException //gibt die Gesamtdaten der Welt fürs befüllen der Felder aller Länder zusammen  
	{
		String sql1 = "select * from coronadata order by epoch desc LIMIT 187";
		int data = 0;
		PreparedStatement stmt;
		ResultSet rs;
		stmt = con.prepareStatement(sql1);
		rs = stmt.executeQuery();
		while (rs.next()) {
			data = data + rs.getInt(textFromLabel);
		}
		
		return data;
	}

	public void close() throws SQLException {

		con.close();
	}

}