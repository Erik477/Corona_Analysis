package Main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartGraphicsController implements Initializable {

	@FXML
	private Button startButton;
	@FXML
	private Button closeButton;
	@FXML
	private Button exit;
	@FXML
	private Button minimize;
	
	private ConfirmBox confirmBox;
	private StartGraphicsMain startScreen;
	private ScenebuilderMain mainScreen;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void startApplication() throws Exception {
		
		mainScreen = new ScenebuilderMain();
		
		mainScreen.start();

	}
}
