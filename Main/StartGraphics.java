package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		
		StackPane layout = new StackPane();
		layout.getChildren().addAll(startButton, closeButton);
		Scene scene = new Scene(layout, 500,500);
		window.setScene(scene);
		window.show();
	}
}
