package Main;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @version 0.75
 * @since 18.03.2020
 * @author Erik
 *
 */

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		StartGraphics sg = new StartGraphics();

		sg.launch(StartGraphics.class, args);
	}
}
