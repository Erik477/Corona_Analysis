package Main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.ObservableList;


public class Mysqlzwei {
	
private Connection con;

public Mysqlzwei() throws ClassNotFoundException, SQLException {

	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(
			"jdbc:mysql://192.168.0.176:3306/Corona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "3r!k");
}
}
