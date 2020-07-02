package Main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
	@FXML
	private Button VergleichButton;
	
	private Stage window;

	@FXML
	private BorderPane bp;
	
	private double xOffset = 0;
	private double yOffset = 0;
	private StartGraphicsMain startScreen = new StartGraphicsMain();
	private ScenebuilderMain mainScreen = new ScenebuilderMain();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void startApplication() throws Exception {
		mainScreen.start();
		StartGraphicsMain.getWindow().close();

	}
	
	public void closeApplication() throws Exception{
		startScreen.close();
	}
	public void minimizeApplication() {
		StartGraphicsMain.getWindow().setIconified(true);
	}
	public void onMousePressedBorderPane()
	{
		bp.setOnMousePressed(new EventHandler<MouseEvent>() {
	         @Override
	         public void handle(MouseEvent event) {
	             xOffset = event.getSceneX();
	             yOffset = event.getSceneY();
	         }
	     });
	}
	public void onMouseDraggedBorderPane()
	{
		bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               StartGraphicsMain.getWindow().setX(event.getScreenX() - xOffset);
               StartGraphicsMain.getWindow().setY(event.getScreenY() - yOffset);
            }
        });


	}
}
