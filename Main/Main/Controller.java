package Main;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class Controller implements Initializable {

	private Mysql mq;

	ObservableList list = FXCollections.observableArrayList();
	@FXML
	private ListView<String> countryList;

	@FXML
	private Button todayCases;

	@FXML
	private LineChart<String, Number> lineChart;
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		try {
			addList();
			todayCasesButton();
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	public void todayCasesButton()
	{
		todayCases = new Button();
		todayCases.setStyle("-fx-background-color: transparent;");
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
	public void Deathclicked() throws ClassNotFoundException, SQLException {
	System.out.println("Death");	
	}
	public void todaydeathclicked() throws ClassNotFoundException, SQLException {
		System.out.println("todayDeath");
	}
	public void Casesclicked() throws ClassNotFoundException, SQLException {
		System.out.println("cases");
	}
	public void todaycaseshclicked() throws ClassNotFoundException, SQLException {
		System.out.println("todaycases");
	}
	public void Criticalclicked() throws ClassNotFoundException, SQLException {
		System.out.println("critical");
	}
	public void recoveredclicked() throws ClassNotFoundException, SQLException {
		System.out.println("recovered");
	}
	public void Activeclicked() throws ClassNotFoundException, SQLException {
		System.out.println("active");
	}
	public void CasesperoneMillionclicked() throws ClassNotFoundException, SQLException {
		System.out.println("million");
	}
	
	public String convertDate(long epoch) {
				
		Date date = new Date(epoch);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		String formatted = format.format(date);
		format.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));//your zone
		formatted = format.format(date);
		System.out.println(formatted); 
		return formatted;
	}
	public void casesChart(ActionEvent event) throws SQLException
	{
		lineChart.setAnimated(false);
		lineChart.getData().clear();
		XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
		
		ArrayList<Long> date = mq.getDate();
		ArrayList<String> dateX = new ArrayList<String>();
		
		ArrayList<Integer> cases = mq.getCases();
		
		
		for (int i = 0; i < date.size(); i++) {
			long epoch = date.get(i);
			String convert = convertDate(epoch);
			System.out.println(convert);
			dateX.add(convert);
		}
		
		for (int i = 0; i < cases.size(); i++) {
			
			series.getData().add(new XYChart.Data<String, Number>(dateX.get(i), cases.get(i)));
		}
//		series.getData().add(new XYChart.Data<String, Number>("Lol", 10));
//		series.getData().add(new XYChart.Data<String, Number>("Lole", 55));
//		series.getData().add(new XYChart.Data<String, Number>("Lolee", 55));
//		series.getData().add(new XYChart.Data<String, Number>("Loleee", 1500));
//		series.getData().add(new XYChart.Data<String, Number>("Loleeee", 500));

		lineChart.getData().add(series);

	}
}