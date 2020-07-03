package Main;

import java.io.IOException;

import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ConfirmExitBox {

	static boolean answer;

	@FXML
	private Button ok;
	@FXML
	private Button cancel;
	@FXML
	private Button swithScene;

	private StartGraphicsMain sg = new StartGraphicsMain();
	private static Stage window;
	private Mysql mq;
	public boolean display() throws IOException {

		window = new Stage(StageStyle.UNDECORATED);
		Parent root = FXMLLoader.load(getClass().getResource("ConfirmBoxFXML.fxml"));
		Scene scene = new Scene(root);

		ok = new Button();
		cancel = new Button();
		swithScene = new Button();

		Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
		window.getIcons().add(icon);

		window.setFullScreen(false);
		window.setScene(scene);

		window.setResizable(true);
		window.initModality(Modality.APPLICATION_MODAL);
		window.show();

		return answer;
	}

//	public void okPressed(int klasse) {
//		answer = true;
//		window.close();
//		System.out.println("CLOSING APPLICATION");
//		
//		switch(klasse)
//		{
//		case 1: StartGraphicsMain.getWindow().close(); break;
//		case 2: ScenebuilderMain.getWindow().close(); break;
//		}
//	}
	public void okPressed() throws ClassNotFoundException, SQLException
	{
		System.out.println("Database / Application closed");
		
		System.exit(0);
	}
	
	public void cancelPressed() {
		answer = false;
		window.close();
		System.out.println("CANCEL CLOSING APPLICATION");
	
	}

}