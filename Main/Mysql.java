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
				"jdbc:mysql://localhost/coronedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "admin");


	}




	public ArrayList<String> getCountry() throws SQLException {

		ArrayList<String> countr = new ArrayList<>();
		PreparedStatement stmt;
		ResultSet rs;

		String Name;
		String format;
		String confirmedCases;
		int confirmed = 0;
		
		String sql = "SELECT * FROM coronadata INNER JOIN (SELECT country, Max(cases) AS Maxscore FROM coronadata\r\n" + 
				"		GROUP BY\r\n" + 
				"			country) topscore ON coronadata.country = topscore.country and topscore.maxscore = coronadata.cases group by topscore.country order by cases desc;";
		
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
			if (confirmed!=0) {
			countr.add(format);
			}
		}
		stmt.close();
		rs.close();
		// return countr;
		System.out.println(countr);
		return countr;

	}

	public ArrayList<Long> getDate(String output) throws SQLException {

		ArrayList<Long> date = new ArrayList<Long>();
		String sql = "select * from coronadata where country = '" + output + "' order by epoch";
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

	public ArrayList<Integer> getInfoList(String input, String output) throws SQLException {          //für den Graphen

		ArrayList<Integer> infoList = new ArrayList<Integer>();
		String sql = "select * from coronadata where country = '" + output + "' order by epoch";
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
	public int getInfo(String input, String output) throws SQLException {            //für die einzelnen TextFelder

		
		String sql = "SELECT * FROM coronadata INNER JOIN (SELECT country, Max(cases) AS Maxscore FROM coronadata\r\n" + 
				"		GROUP BY\r\n" + 
				"			country) topscore ON coronadata.country = topscore.country and topscore.maxscore = coronadata.cases and topscore.country = '" + output + "' group by topscore.country order by cases desc;";

		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		int info = 0;
		while (rs.next()) {
			info = rs.getInt(input.toLowerCase());
			
		}
		stmt.close();
		rs.close();
		return info;
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
		String sql = "select * from coronadata where country = 'world'";
		
		int data = 0;
		PreparedStatement stmt;
		ResultSet rs;
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			data = rs.getInt(textFromLabel);
		}
		
		return data;
	}

	public void insertWorld() throws SQLException
	{
		String sql = "SELECT * FROM coronadata INNER JOIN (SELECT country, Max(cases) AS Maxscore FROM coronadata\r\n" + 
				"		GROUP BY\r\n" + 
				"			country) topscore ON coronadata.country = topscore.country and topscore.maxscore = coronadata.cases group by topscore.country order by cases desc;";
		
		int worldCases = 0;
		int worldCasesToday = 0;
		int worldDeaths = 0;
		int worldDeathsToday = 0;
		int worldActive = 0;
		int worldCritical = 0;
		int worldRecovered = 0;
		String country = "World";
		PreparedStatement stmt;
		ResultSet rs;
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			worldCases = worldCases +rs.getInt("cases");
			worldCasesToday = worldCasesToday + rs.getInt("todayCases");
			worldDeaths =  worldDeaths +rs.getInt("deaths");
			worldDeathsToday = worldDeathsToday +rs.getInt("todayDeaths");
			worldActive =  worldActive + rs.getInt("active");
			worldCritical = worldCritical + rs.getInt("critical");
			worldRecovered = worldRecovered +rs.getInt("recovered");
		}
		
		String sql1 = "insert into coronadata(epoch,country, cases, todayCases, deaths, todayDeaths, recovered, active, critical) values (1585805755819, '" + country + "', '"+ worldCases + "', '" + worldCasesToday + "', '" + worldDeaths + "', '"+worldDeathsToday + "', '" + worldRecovered + "', '" +worldActive + "', '" + worldCritical + "')";
	stmt = con.prepareStatement(sql1);
	stmt.execute();
	stmt.close();
	
	System.out.println(worldCases);
	System.out.println(worldCasesToday);
	}
	public void close() throws SQLException {

		con.close();
	}

}