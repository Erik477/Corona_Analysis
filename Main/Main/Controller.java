package Main;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class Controller implements Initializable{
	
	ObservableList list = FXCollections.observableArrayList();
	@FXML
	private ListView<String> countryList;

	private Mysql mq;
	public void loginButtonClicked() {

		System.out.println("User logged in");
	}
	

	public void addList() throws ClassNotFoundException, SQLException{
		//listview.getItems().addAll("China","Italy","Spain","Germany","Iran","USA","France","S. Korea","Switzerland","UK","Netherlands","Belgium","Austria","Norway","Sweden","Denmark","Portugal","Malaysia","Canada","Australia","Brazil","Japan","Czechia","Israel","Pakistan","Diamond Princess","Ireland","Turkey","Luxembourg","Chile","Finland","Greece","Iceland","Qatar","Poland","Indonesia","Singapore","Ecuador","Thailand","Saudi Arabia","Slovenia","Romania","Philippines","Russia","Estonia","Bahrain","Egypt","India","Hong Kong","Peru","South Africa","Iraq","Lebanon","Croatia","Mexico","Panama","Kuwait","Bulgaria","Armenia","Argentina","Colombia","Taiwan","United Arab Emirates","San Marino","Serbia","Slovakia","Latvia","Costa Rica","Dominican Republic","Uruguay","Hungary","Algeria","Vietnam","Bosnia and Herzegovina","Faeroe Islands","Andorra","Morocco","Jordan","North Macedonia","Cyprus","Lithuania","Brunei","Moldova","Sri Lanka","Albania","Belarus","Malta","Venezuela","Burkina Faso","Tunisia","Azerbaijan","Cambodia","Kazakhstan","New Zealand","Oman","Palestine","Guadeloupe","Georgia","Trinidad and Tobago","Senegal","Réunion","Ukraine","Uzbekistan","Liechtenstein","Martinique","Cameroon","Bangladesh","Afghanistan","Honduras","Democratic Republic of the Congo","Nigeria","Cuba","Jamaica","Bolivia","Ghana","Paraguay","Macao","Puerto Rico","Rwanda","Togo","French Guiana","French Polynesia","Guam","Mauritius","Channel Islands","Ivory Coast","Kyrgyzstan","Montenegro","Guatemala","Maldives","Monaco","Gibraltar","Mongolia","Ethiopia","Kenya","Mayotte","Seychelles","Barbados","Equatorial Guinea","Tanzania","U.S. Virgin Islands","Guyana","Aruba","Gabon","Bahamas","New Caledonia","Saint Martin","Suriname","Cayman Islands","Curaçao","Cabo Verde","CAR","Congo","El Salvador","Liberia","Madagascar","Namibia","St. Barth","Zimbabwe","Sudan","Angola","Benin","Bermuda","Bhutan","Fiji","Greenland","Guinea","Haiti","Isle of Man","Mauritania","Nicaragua","Saint Lucia","Zambia","Nepal","Antigua and Barbuda","Chad","Djibouti","Gambia","Vatican City","Montserrat","Niger","Papua New Guinea","St. Vincent Grenadines","Sint Maarten","Somalia","Eswatini","Timor-Leste");
		//Mysql mq = new Mysql();
		//ArrayList<String> country = mq.getCountry();
		
		mq = new Mysql();
		list.removeAll(list);
	
		ArrayList<String> country = mq.getCountry();
		for (int i = 0; i < country.size(); i++) {
			
			list.addAll(country.get(i));
		}
		
		//list.addAll("China","Italy","Spain","Germany","Iran","USA","France","S. Korea","Switzerland","UK","Netherlands","Belgium","Austria","Norway","Sweden","Denmark","Portugal","Malaysia","Canada","Australia","Brazil","Japan","Czechia","Israel","Pakistan","Diamond Princess","Ireland","Turkey","Luxembourg","Chile","Finland","Greece","Iceland","Qatar","Poland","Indonesia","Singapore","Ecuador","Thailand","Saudi Arabia","Slovenia","Romania","Philippines","Russia","Estonia","Bahrain","Egypt","India","Hong Kong","Peru","South Africa","Iraq","Lebanon","Croatia","Mexico","Panama","Kuwait","Bulgaria","Armenia","Argentina","Colombia","Taiwan","UAE","San Marino","Serbia","Slovakia","Latvia","Costa Rica","Dominican Republic","Uruguay","Hungary","Algeria","Vietnam","Bosnia and Herzegovina","Faeroe Islands","Andorra","Morocco","Jordan","North Macedonia","Cyprus","Lithuania","Brunei","Moldova","Sri Lanka","Albania","Belarus","Malta","Venezuela","Burkina Faso","Tunisia","Azerbaijan","Cambodia","Kazakhstan","New Zealand","Oman","Palestine","Guadeloupe","Georgia","Trinidad and Tobago","Senegal","Réunion","Ukraine","Uzbekistan","Liechtenstein","Martinique","Cameroon","Bangladesh","Afghanistan","Honduras","DRC","Nigeria","Cuba","Jamaica","Bolivia","Ghana","Paraguay","Macao","Puerto Rico","Rwanda","Togo","French Guiana","French Polynesia","Guam","Mauritius","Channel Islands","Ivory Coast","Kyrgyzstan","Montenegro","Guatemala","Maldives","Monaco","Gibraltar","Mongolia","Ethiopia","Kenya","Mayotte","Seychelles","Barbados","Equatorial Guinea","Tanzania","U.S. Virgin Islands","Guyana","Aruba","Gabon","Bahamas","New Caledonia","Saint Martin","Suriname","Cayman Islands","Curaçao","Cabo Verde","CAR","Congo","El Salvador","Liberia","Madagascar","Namibia","St. Barth","Zimbabwe","Sudan","Angola","Benin","Bermuda","Bhutan","Fiji","Greenland","Guinea","Haiti","Isle of Man","Mauritania","Nicaragua","Saint Lucia","Zambia","Nepal","Antigua and Barbuda","Chad","Djibouti","Gambia","Vatican City","Montserrat","Niger","Papua New Guinea","St. Vincent Grenadines","Sint Maarten","Somalia","Eswatini","Timor-Leste");
		countryList.getItems().addAll(list);
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			addList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
