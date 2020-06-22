package Main;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartGraphicsMain extends Application {

	private static Stage window;
	private static Scene scene;
	
	public StartGraphicsMain() {
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = new Stage();

		Parent root = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
		scene = new Scene(root);

		scene.getStylesheets().add(getClass().getResource("StartStyles.css").toString());
		

		Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
		window.getIcons().add(icon);
		window.setTitle("COVID 19");
		window.setFullScreen(false);
		window.setScene(scene);
		window.initStyle(StageStyle.TRANSPARENT);
		
		window.setResizable(true);
		window.show();

		
	}
	

	
	
	public static Stage getWindow() {
		return window;
	}
	public void setWindow(Stage window) {
		this.window = window;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void close() throws IOException
	{
		ConfirmExitBox exitBox = new ConfirmExitBox();
		boolean result = exitBox.display();
		
		if(result)
		{
			window.close();
		}
	}
	public void minimize()
	{
		window.hide();
	}
	

}