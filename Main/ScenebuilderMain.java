package Main;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScenebuilderMain {
	


	private static Stage window;
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

	private Scene scene;

	
		public void start() throws Exception {
			// TODO Auto-generated method stub
			
			window = new Stage(StageStyle.UNDECORATED);
			Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("MainStyles.css").toString());
			
			Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
			window.getIcons().add(icon);
			window.setTitle("Hello There");
			window.setFullScreen(false);
			window.setScene(scene);
			
			window.setResizable(true);
			
			window.show();
		}
		
		

			}

