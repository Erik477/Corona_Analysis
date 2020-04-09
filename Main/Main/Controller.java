package Main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class Controller implements Initializable {

	Mysql mq;
	

	ObservableList list = FXCollections.observableArrayList();
	@FXML
	private ListView<String> countryList;
 

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		
		try {
			addList();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	public void loginButtonClicked() {

		System.out.println("User logged in");
	}

	public void addList() throws ClassNotFoundException, SQLException {
		mq = new Mysql();


		

		countryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


		list.removeAll(list);

		ArrayList countries = mq.getCountry();
		for (int i = 0; i < countries.size(); i++) {

			list.addAll(countries.get(i));
		}

		countryList.getItems().addAll(list);

	}

}
