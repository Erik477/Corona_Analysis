package Main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartGraphics extends Application{

	
	Button startButton;
	Button closeButton;
	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("COVID-19 Analysis by Erik and Fubus");
		
		startButton = new Button("START");
		
		closeButton = new Button("CLOSE");
		closeButton.setOnAction(e -> new CloseAlertBox().display("CLOSE", "Are you sure you want to quit?"));
		
		
		//Hintergrundbild
		
		Image image = new Image("startup_wallpaper.jpg", 500, 500, false, false);
		ImageView mv = new ImageView(image);
		
		
		
		StackPane layout = new StackPane();
		layout.getChildren().addAll(mv, startButton, closeButton);
		Scene scene = new Scene(layout, 500,500);
		
		
		window.setScene(scene);
		window.setResizable(false);
		window.sizeToScene();   //verhindert das kein Rand durch .setResizable entsteht
		window.show();
	}
}
