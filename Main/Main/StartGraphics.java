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

	private int WIDTH = 700, HEIGHT = 450;
	@Override
	public  void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("COVID-19 Analysis by Erik and Fubus");
		
		startButton = new Button("START");
		startButton.setMinSize(200, 50);
		startButton.setLayoutX(WIDTH /2 - 100);
		startButton.setLayoutY(HEIGHT - 260);
		
		
		
		
		closeButton = new Button("CLOSE");
		closeButton.setOnAction(e -> new CloseAlertBox().display("CLOSE", "Are you sure you want to quit?"));
		closeButton.setMinSize(200, 50 );
		closeButton.setLayoutX(WIDTH/2 - 100 );
		closeButton.setLayoutY(HEIGHT - 200);
		
		//Hintergrundbild
		
		Image image = new Image("file:coronavirus.jpg");
		ImageView mv = new ImageView(image);
		
		
		
		Group layout = new Group();
		layout.getChildren().addAll( mv,startButton, closeButton);
		Scene scene = new Scene(layout, 700,450);
		
		
		window.setScene(scene);
		window.setResizable(false);
		window.sizeToScene();   //verhindert das kein Rand durch .setResizable entsteht
		window.show();
	}
}
