package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ScenebuilderMain {
	

		private Stage window;
	
		public void start() throws Exception {
			// TODO Auto-generated method stub
			
			window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
			window.setTitle("Hello There");
			window.setScene(new Scene(root, 800, 500));
			window.show();
		}

	}


