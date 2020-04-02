package Main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Graphics {
	Stage window;
	ListView<String> listview;
	Mysql mq;
	String countr = "";
	ObservableList<String> cunt;

	final int listViewRowHeight = 20;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	public void start() throws Exception {

		window = new Stage();

		mq = new Mysql();
		ArrayList<String> country = mq.getCountry();

		window.setTitle("Main");
		BorderPane borderPane = new BorderPane();

		//Image image = new Image("file:startup_wallpaper.jpg");
		// ImageView mv = new ImageView(image);

		

		

		

		VBox left = new VBox(10);
		
		
		
		ListViewObj list = new ListViewObj(mq);
		Label label = new Label(countr);
		label.setStyle("-fx-background: #2261c7;");
		GridPane.setConstraints(label, 500, 500);
		label.setPadding(new Insets(10, 10, 10, 10));

		left.getChildren().addAll(list);

		borderPane.setTop(label);
		borderPane.setLeft(left);
		
		// borderPane.setCenter(mv);

		borderPane.setStyle("-fx-background: #241918;");

		Scene scene = new Scene(borderPane, (int) width, (int) height -40);
		
		
		
		window.setResizable(false);
		window.setScene(scene);
		window.show();

	}

	private void Clicked() {

		cunt = listview.getSelectionModel().getSelectedItems();
		countr = "";
		for (String m : cunt) {
			countr += m;
		}

		System.out.println(countr);

	}
	
	
	
	
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getListViewRowHeight() {
		return listViewRowHeight;
	}

}