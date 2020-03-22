package testling;

import java.sql.SQLException;

public class Testus {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		jdbc db = new jdbc();
		
		db.getInfected();
		
		
		
		db.close();
	}

}
