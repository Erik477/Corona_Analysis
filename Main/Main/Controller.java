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
	private Button Cases, todayCases, casesPerOneMillion, Recovered, Critical, Active, todayDeaths, Deaths;

	private ArrayList<Button> chartButtons;
	@FXML
	private LineChart<String, Number> lineChart;

	String value = "";

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		try {
			addList();
			todayCasesButton();
			infoChart();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	public void addButtonsToList() {

		Cases = new Button("Cases");
		todayCases = new Button("todayCases");
		casesPerOneMillion = new Button("casesPerOneMillion");
		Recovered = new Button("Recovered");
		Critical = new Button("Critical");
		Active = new Button("Active");
		todayDeaths = new Button("todayDeaths");
		Deaths = new Button("Deaths");

		chartButtons = new ArrayList<Button>();
		chartButtons.add(todayCases);
		chartButtons.add(Cases);
		chartButtons.add(casesPerOneMillion);
		chartButtons.add(Recovered);
		chartButtons.add(Critical);
		chartButtons.add(Active);
		chartButtons.add(todayDeaths);
		chartButtons.add(Deaths);

	}

	public void todayCasesButton() {
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

	public void infoChart() throws SQLException {
		lineChart.setAnimated(false);
		
		lineChart.getData().clear();
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

		ArrayList<Long> date = mq.getDate();
		ArrayList<String> dateX = new ArrayList<String>();

		addButtonsToList();

		chartButtons = new ArrayList<Button>();
	
		for (Button b : chartButtons) {

			b.setOnAction(e -> {

				value = b.getText();
				System.out.println(value);

			});

		}
		ArrayList<Integer> cases = mq.getInfo("todayDeaths");

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