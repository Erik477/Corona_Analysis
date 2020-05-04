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
	private Button Cases, casesPerOneMillion, Recovered, Critical, Active, Deaths;
	private ArrayList<Button> chartButtons;
	@FXML
	private LineChart<String, Number> lineChart;

	String value = "";

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		try {
			addList();
			
		//	addButtonsToList();
			
			Cases.setOnAction(e -> {

				value = "Cases";
				System.out.println(value);

				try {
					infoChart(value);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			Deaths.setOnAction(e -> {

				value = "Deaths";
				System.out.println(value+"Button");

				try {
					infoChart(value);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			casesPerOneMillion.setOnAction(e -> {

				value = "casesPerOneMillion";
				System.out.println(value+"Button");

				try {
					infoChart(value);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			Recovered.setOnAction(e -> {

				value = "Recovered";
				System.out.println(value+"Button");
				try {
					infoChart(value);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			Active.setOnAction(e -> {

				value = "Active";
				System.out.println(value+"Button");
				try {
					infoChart(value);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			Critical.setOnAction(e -> {

				value = "Critical";
				System.out.println(value+"Button");
				try {
					infoChart(value);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	public void addButtonsToList() {

		Cases = new Button("Cases");
		casesPerOneMillion = new Button("casesPerOneMillion");
		Recovered = new Button("Recovered");
		Critical = new Button("Critical");
		Active = new Button("Active");
		Deaths = new Button("Deaths");


		chartButtons.add(Cases);
		chartButtons.add(casesPerOneMillion);
		chartButtons.add(Recovered);
		chartButtons.add(Critical);
		chartButtons.add(Active);
		chartButtons.add(Deaths);

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

	public String convertDate(long epoch) {

		Date date = new Date(epoch);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		String formatted = format.format(date);
		format.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));// your zone
		formatted = format.format(date);
		System.out.println(formatted);
		return formatted;
	}

	public void infoChart(String value) throws SQLException {
		lineChart.setAnimated(false);
		
		lineChart.getData().clear();
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		ArrayList<Long> date = mq.getDate();
		ArrayList<String> dateX = new ArrayList<String>();
		
		chartButtons = new ArrayList<Button>();
		
		addButtonsToList();

		System.out.println(value);

		ArrayList<Integer> cases = mq.getInfo(value);	//müssma halt no an string rein machn der sich ja nach button ändert


		for (int i = 0; i < date.size(); i++) {
			long epoch = date.get(i);
			String convert = convertDate(epoch);
			System.out.println(convert);
			
			dateX.add(convert);
		}
		
		System.out.println(cases);

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
