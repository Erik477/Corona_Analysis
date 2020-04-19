package Main;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		StartGraphics sg = new StartGraphics();
		Graphics mg = new Graphics();
		
		
				sg.launch(StartGraphics.class, args);
		//mg.launch(Graphics.class, args);
	}
}