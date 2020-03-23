package Main;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		Graphics graphics = new Graphics();
		
		
		MySQL db = new MySQL();
		ArrayList<String> countryNames = db.getName();
		
		for(int i = 0;i < countryNames.size(); i++	)
		{
			System.out.println(countryNames.get(i));
		}

	}

}
