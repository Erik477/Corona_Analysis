package Main;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListViewObj extends ListView {

	String countr = "";
	private Mysql mq;
	final int ROW_HEIGHT = 24;
	final ObservableList items = FXCollections.observableArrayList("1", "2", "3");
	final ListView list = new ListView(items);

	

	public ListViewObj(Mysql mq) throws SQLException {
		this.mq = mq;
		initalize(mq);	
	}
	
	

	public void initalize(Mysql mq) throws SQLException
	{
		//graphics = new Graphics();
		ArrayList<String> country = mq.getCountry();
		country.add("World");
		
		for (int i = 0; i < country.size(); i++) {
			String e = country.get(i);
			getItems().add(e);
		
			//setPrefHeight(graphics.getHeight());
			

			getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        // Your action here
			        countr = newValue;
			       
			    }
			});
			
		}
	}
}
