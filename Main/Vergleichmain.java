package Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Vergleichmain {
	private static Stage window;
	private static Scene scene;
	
	public Vergleichmain() {
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = new Stage();

		Parent root = FXMLLoader.load(getClass().getResource("Vergleich.fxml"));
		scene = new Scene(root);

		//scene.getStylesheets().add(getClass().getResource("StartStyles.css").toString());
		

		Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
		window.getIcons().add(icon);
		window.setTitle("COVID 19");
		window.setFullScreen(false);
		window.setScene(scene);
		window.initStyle(StageStyle.TRANSPARENT);
		
		window.setResizable(true);
		window.show();

		
	}
}
