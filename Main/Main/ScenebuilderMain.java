package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScenebuilderMain {
	

		private Stage window;
	
		public void start() throws Exception {
			// TODO Auto-generated method stub
			
			window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/styles.css").toString());
			
			Image icon = new Image(getClass().getResourceAsStream("labs.png"));
			window.getIcons().add(icon);
			window.setTitle("Hello There");
			window.setFullScreen(false);
			window.setScene(scene);
			
			window.setResizable(true);
			window.show();
		}

					}