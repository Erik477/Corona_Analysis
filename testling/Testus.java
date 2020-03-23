package testling;


import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Testus extends Application {

	Button b;
	jdbc db;
	
	int infected;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		
		
		
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		db = new jdbc();
		primaryStage.setTitle("Hello");
		
		infected = db.getInfected();
		String	infect = Integer.toString(infected);
		
		 TextField t = new TextField();
		 t.setText(infect);
		 t.setEditable(false);
		 t.setPrefWidth(100);
		
		 t.setBorder(null);
		 t.setLayoutX(100);
		 t.setLayoutY(100);
		 
		 b = new Button();
		 b.setText("Österreich");
		 
		 Group g = new Group();
			
		g.getChildren().add(b);
		
		 b.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			      System.out.println("funkt");
			      g.getChildren().add(t);
			    }
			});
		
		
		
		
		
		
		Scene scene = new Scene(g, 1920,1080);
		scene.setFill(Color.DARKGRAY);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

}
