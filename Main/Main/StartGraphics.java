package Main;

import java.awt.Font;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartGraphics extends Application{

	
	private Button startButton;
	private Button closeButton;
	private Button exitButton, minimizeButton , fullscreenButton;
	private Stage window;
	private ScenebuilderMain graphics;
	private int WIDTH = 700, HEIGHT = 450;
	private int minButtonTopX = 10, maxButtonTopX = 40, prefButtonTopX = 20;
	private int minButtonTopY = 10, maxButtonTopY = 40, prefButtonTopY = 20;
	
	@Override
	public  void start(Stage primaryStage) throws Exception {
		
		 graphics = new ScenebuilderMain();
		
		window = new Stage(StageStyle.UNDECORATED);
		
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
		
		
		
		exitButton = new Button("X");		
		exitButton.setPrefSize(20, 20);
		exitButton.setStyle("-fx-background-color: transparent;");
		exitButton.setTextFill(Color.WHITE);
		exitButton.setTranslateX(WIDTH -prefButtonTopX *2);
		exitButton.setOnAction(e ->{
		boolean result = ConfirmBox.display("Close?", "Are you sure you want to exit?");
			
			if(result)
			{
				window.close();
			}
		});
		
		minimizeButton = new Button("_");
		minimizeButton.setPrefSize(20,20);
		minimizeButton.setStyle("-fx-background-color: transparent;");
		minimizeButton.setTextFill(Color.WHITE);
		minimizeButton.setTranslateY(0);
		minimizeButton.setTranslateX(WIDTH -prefButtonTopX *2 + 30);
		
		fullscreenButton = new Button("+");
		fullscreenButton.setPrefSize(20, 20);
		fullscreenButton.setStyle("-fx-background-color: transparent;");
		fullscreenButton.setTextFill(Color.WHITE);
		fullscreenButton.setTranslateX(0);
		fullscreenButton.setTranslateY(WIDTH -prefButtonTopX *2 -60);
		
		HBox topBar = new HBox(90);
		topBar.setAlignment(Pos.TOP_RIGHT);
		topBar.getChildren().addAll(exitButton, minimizeButton, fullscreenButton);
				
		
		//Hintergrundbild
		
		Image image = new Image("file:coronavirus.jpg");
		ImageView mv = new ImageView(image);
		
		
		
		Group layout = new Group();
		layout.getChildren().addAll( mv,startButton, closeButton, topBar);
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