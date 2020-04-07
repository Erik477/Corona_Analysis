package Main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartGraphics extends Application{

	
	private Button startButton;
	private Button closeButton;
	private Stage window;
	private ScenebuilderMain graphics;
	private int WIDTH = 700, HEIGHT = 450;
	
	
	@Override
	public  void start(Stage primaryStage) throws Exception {
		
		 graphics = new ScenebuilderMain();
		
		window = primaryStage;
		window.setOnCloseRequest(e-> {
			e.consume();
			closeProgram();
		});
		window.setTitle("COVID-19 Analysis by Erik and Fubus");
		
		startButton = new Button("START");
		startButton.setMinSize(200, 50);
		startButton.setLayoutX(WIDTH /2 - 100);
		startButton.setLayoutY(HEIGHT - 260);
		startButton.setOnAction(e ->{ 
			try {
				graphics.start();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			window.close();
		});
		
		
		
		closeButton = new Button("CLOSE");
		closeButton.setMinSize(200, 50 );
		closeButton.setLayoutX(WIDTH/2 - 100 );
		closeButton.setLayoutY(HEIGHT - 200);
		
		closeButton.setOnAction(e-> {
		boolean result = ConfirmBox.display("Close?", "Are you sure you want to exit?");
			
			if(result)
			{
				window.close();
			}
		});
		
		
		
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
	
	private void closeProgram()
	{
		Boolean answer = ConfirmBox.display("Title", "You wanna exit");
		if(answer)
		{
		window.close();
		}
	}
}