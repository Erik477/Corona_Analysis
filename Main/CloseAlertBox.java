package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CloseAlertBox {

	
	public static void display(String title, String message)
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("title");
		window.setMaxWidth(300);
		
		Label label = new Label();
		label.setText(message);
		label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> System.exit(0));
		
	
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(closeButton, label);
		layout.setAlignment(Pos.CENTER );
		
		Scene scene = new Scene(layout);
		
		
		window.setScene(scene);
		window.showAndWait();
		
	}
}
