package JavaFXTut;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Test extends Application   {

	Button button;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Title");
		button = new Button();
		button.setText("Button");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("lol");
			}
			
		});
		

		StackPane layout = new StackPane();
		layout.getChildren().add(button);

		Scene scene = new Scene(layout);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}