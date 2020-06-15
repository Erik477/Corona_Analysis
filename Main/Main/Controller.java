package Main;


import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.sun.javafx.geom.Rectangle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;


public class Controller implements Initializable {

	private Mysql mq;

	private ObservableList list = FXCollections.observableArrayList();

	// -----------------------------------------------
	@FXML
	private ListView<String> countryList;
	@FXML
	private Button Cases, casesPerOneMillion, Recovered, Critical, Active, Deaths;
	private ArrayList<Button> chartButtons;
	@FXML
	private LineChart<String, Number> lineChart;

	@FXML
	private Button submit;

	@FXML
	private TextField totalConfirmed;
	@FXML
	private TextField totalConfirmedNumber;


	// -----------------------------------------------
	private String value = "";
	private String country = "";
	private String output = "world";


	private Button About;
	@FXML
	private Button Claim;

	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		System.out.println("i should be there");
		try {
			addList(); // fügt die ListView mit Ländernamen und infiziertenzahlen ein
			chartButtons = new ArrayList<>();
			addButtonsToList(); // fügt die Buttons zur ArrayList hinu
			addTextContent();
			for (Button b : chartButtons) {
				b.setOnAction(e -> {

					value = b.getText(); // übergeben des Button Texts für den Graphen
					try {
						infoChart(value); // zeichnen vom Graphen 'value'
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				});
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void addcountrydata() {
		
		}

	public void addButtonsToList() {

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

	public String convertDate(long epoch) { // konvertieren der epoch zahl in ein gültiges Zeitformat

		Date date = new Date(epoch);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		String formatted = format.format(date);
		format.setTimeZone(TimeZone.getTimeZone("Europe/Vienna"));// your zone
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

		System.out.println(value);

		ArrayList<Integer> info = mq.getInfo(value, output); 
																

		lineChart.setCreateSymbols(false);

		for (int i = 0; i < date.size(); i++) {
			long epoch = date.get(i);
			String convert = convertDate(epoch);
			System.out.println(convert);

			dateX.add(convert);
		}

		lineChart.setTitle(value);
		System.out.println(info);

		for (int i = 0; i < info.size(); i++) {

			series.getData().add(new XYChart.Data<String, Number>(dateX.get(i), info.get(i)));
		}

		lineChart.getData().add(series);
	}

	public void countryClicked() { // Methode die aufgerufen wird wenn ein Column in der Länderliste angeklickt
									// wird --> Name vom Land wird für den Graphen übertragen
		String output1 = "";
		String output2 = "";
		ObservableList<String> countries;
		countries = countryList.getSelectionModel().getSelectedItems();

		for (String s : countries) {
			output1 = s;
		}
		output2 = output1.replaceAll("\\d", "");
		output = output2.replaceAll("\\s+", "");
		System.out.println(output);

	}

	public void addTextContent() {

		totalConfirmed = new TextField("Total Confirmed");
		
	}
}