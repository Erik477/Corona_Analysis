package Main;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ConfirmExitBox {

	static boolean answer;
	
	@FXML
	private Button ok;
	@FXML
	private Button cancel;
	
	
	public   boolean display() throws IOException
	{
		
		Stage window = new Stage(StageStyle.UNDECORATED);;
		Parent root = FXMLLoader.load(getClass().getResource("ConfirmBoxFXML.fxml"));
		Scene scene = new Scene(root);
		
		ok = new Button();
		cancel = new Button();
		
		ok.setOnAction(e -> {
			
			answer = true;
			System.out.println("CLOSING APPLICATION");
			window.close();
		});
		cancel.setOnAction(e -> {
			answer = false;
			window.close();
		});
		

		
		
		Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
		window.getIcons().add(icon);
		
		window.setFullScreen(false);
		window.setScene(scene);
		
		window.setResizable(true);
		
		window.show();
		
		return answer;
	}
	
	
}
