package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CloseAlertBox {

	
	public static void display(String title, String message)
	{
		Stage window = new Stage(StageStyle.UNDECORATED);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMaxWidth(300);
		
		Label label = new Label();
		label.setAlignment(Pos.BOTTOM_CENTER);
		label.setText(message);
		//label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(e -> System.exit(0));
		
		Button noButton = new Button ("No");
		noButton.setOnAction(e -> window.close());
		
		FlowPane layout = new FlowPane();
		layout.getChildren().addAll(yesButton, noButton, label);
		layout.setAlignment(Pos.CENTER );
		layout.setHgap(25);
		//layout.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		//layout.setStyle("-fx-background-color: #042669");
		
		
		Scene scene = new Scene(layout, 300, 100);
		scene.setFill(Color.TRANSPARENT);
		
	
		window.initStyle(StageStyle.TRANSPARENT);
		window.setScene(scene);
		window.showAndWait();
		
		
	}
}
