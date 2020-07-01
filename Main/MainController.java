package Main;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.sun.javafx.geom.Rectangle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class MainController implements Initializable {

	private Mysql mq;

	private ObservableList list = FXCollections.observableArrayList();

	// -----------------------------------------------
	@FXML
	private ListView<String> countryList;

	// ----------------------------------------------------
	@FXML
	private Button Cases, casesPerOneMillion, Recovered, Critical, Active, Deaths;
	private ArrayList<Button> chartButtons;

	// -----------------------------------------------------

	@FXML
	private LineChart<String, Number> lineChart;

	// --------------------------------------------------

	@FXML
	private Label totalConfirmed;
	@FXML
	private Label totalConfirmedNumber;

	@FXML
	private Label CasesHeadField;
	@FXML
	private Label DeathsHeadField;
	@FXML
	private Label ActiveHeadField;
	@FXML
	private Label RecoveredHeadField;
	@FXML
	private Label CasesNumberField;
	@FXML
	private Label DeathsNumberField;
	@FXML
	private Label ActiveNumberField;
	@FXML
	private Label RecoveredNumberField;
	
	@FXML
	private Label CriticalHeadField;
	@FXML
	private Label TodayCasesHeadField;
	@FXML
	private Label TodayDeathsHeadField;
	@FXML
	private Label CasesPerOneMillionHeadField;
	@FXML
	private Label TodayCasesNumberField;
	@FXML
	private Label TodayDeathsNumberField;
	@FXML
	private Label CriticalNumberField;
	@FXML 
	private Label CasesPerOneMillionNumberField;
	@FXML
	private Label infoTextLabel;
	// -----------------------------------------------
	private String value = "cases";
	private String output = "World";

	// --------------------------------------------------

	private double xOffset = 0;
	private double yOffset = 0;
	// -----------------------------------------------------
	@FXML
	private BorderPane bp;
	// -------------------------------------------------------
	

	private ScenebuilderMain gui = new ScenebuilderMain();
	// --------------------------------------------------------
	@FXML
	private ImageView ig;

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		
//		
//		try {
//			mq=new Mysql();
//			mq.insertWorld();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("i should be there");
		
		try {
			addList(); // fügt die ListView mit Ländernamen und infiziertenzahlen ein
			addButtons(); // fügt die Buttons zur ArrayList hinu
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public void addButtons() {

		chartButtons = new ArrayList<>();
		chartButtons.add(Cases);
		chartButtons.add(casesPerOneMillion);
		chartButtons.add(Recovered);
		chartButtons.add(Critical);
		chartButtons.add(Active);
		chartButtons.add(Deaths);
		
		Cases = new Button("cases");
		Deaths = new Button("deaths");
		casesPerOneMillion = new Button("CasesPerOneMillion");
		Recovered = new Button("Recovered");
		Critical = new Button("Critical");
		Active = new Button("Active");
		
		for(Button b: chartButtons)
		{
			value = b.getText();
		}
		
	}

	public void casesClicked() throws ClassNotFoundException, SQLException
    {
        value = "Cases";
        infoChart(value,output);
    }
    public void deathsClicked() throws ClassNotFoundException, SQLException
    {
        value = "Deaths";
        infoChart(value,output);
    }
    public void casesPerOneMillionClicked() throws ClassNotFoundException, SQLException
    {
        value = "casesPerOneMillion";
        infoChart(value,output);
    }
    public void activeClicked() throws ClassNotFoundException, SQLException
    {
        value = "Active";
        infoChart(value,output);
    }
    public void criticalClicked() throws ClassNotFoundException, SQLException
    {
        value = "Critical";
        infoChart(value,output);
    }
    public void recoveredClicked() throws ClassNotFoundException, SQLException
    {
        value = "Recovered";
        infoChart(value,output);
    }
	
	public void addList() throws ClassNotFoundException, SQLException {
		mq = new Mysql();

		countryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		list.removeAll(list);

		ArrayList countries = mq.getCountry();
		for (int i = 0; i < countries.size(); i++) {

			list.addAll(countries.get(i));
		}

		// mq.insertWorld();
		
		
		countryList.getItems().addAll(list);

	}

	public void infoChart(String value, String output) throws SQLException, ClassNotFoundException {

		mq = new Mysql();
		lineChart.setAnimated(false);
		lineChart.getData().clear();
		

		if (!output.equals("World")) {
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			ArrayList<Long> date = mq.getDate(output);
			ArrayList<String> dateX = new ArrayList<String>();
			chartButtons = new ArrayList<Button>();
			ArrayList<Integer> info = mq.getInfoList(value, output);
			lineChart.setCreateSymbols(false);


			lineChart.setCreateSymbols(false);

			for (int i = 0; i < date.size(); i++) {
				long epoch = date.get(i);
				String convert = mq.convertDate(epoch);
				System.out.println(convert);
				dateX.add(convert);
			}
			lineChart.setTitle(value);
			System.out.println(info);

			for (int i = 0; i < info.size(); i++) {

				series.getData().add(new XYChart.Data<String, Number>(dateX.get(i), info.get(i)));
			}
			lineChart.getData().add(series);
		} else {
			lineChart.setTitle("No data available for 'World'");
		}
	}

	public void countryClicked() throws SQLException, ClassNotFoundException { // Methode die aufgerufen wird wenn ein Column in der Länderliste
														// angeklickt
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
		infoChart(value, output);
		addTextContent();
		addImages();

	}

	public void addTextContent() throws SQLException {

		totalConfirmed.setText("Total Confirmed");

		CasesHeadField.setText("Cases");
		DeathsHeadField.setText("Deaths");
		ActiveHeadField.setText("Active Cases");
		RecoveredHeadField.setText("Recovered");
		CriticalHeadField.setText("Critical");
		TodayCasesHeadField.setText("Today Cases");
		TodayDeathsHeadField.setText("Today Deaths");
		CasesPerOneMillionHeadField.setText("CasesPerOneMillion");
		int totalConfirmed = mq.totalInfo("cases");
		totalConfirmedNumber.setText(Integer.toString(totalConfirmed));

		if (output.equals("World")) {
			CasesNumberField.setText(Integer.toString(mq.totalInfo("cases")));
			DeathsNumberField.setText(Integer.toString(mq.totalInfo("deaths")));
			RecoveredNumberField.setText(Integer.toString(mq.totalInfo("recovered")));
			ActiveNumberField.setText(Integer.toString(mq.totalInfo("active")));
			TodayCasesNumberField.setText(Integer.toString(mq.totalInfo("TodayCases")));
			TodayDeathsNumberField.setText(Integer.toString(mq.totalInfo("TodayDeaths")));
			CriticalNumberField.setText(Integer.toString(mq.totalInfo("Critical")));
			CasesPerOneMillionNumberField.setText(Integer.toString(mq.totalInfo("CasesPerOneMillion")));
		} else {
			if (CasesNumberField.getText().equals("0") && DeathsNumberField.getText().equals("0")
					&& RecoveredNumberField.getText().equals("0") && ActiveNumberField.getText().equals("0")) {
				CasesNumberField.setText("no specific data");
				DeathsNumberField.setText("no specific data");
				RecoveredNumberField.setText("no specific data");
				ActiveNumberField.setText("no specific data");
				TodayCasesNumberField.setText("no specific data");
				TodayDeathsNumberField.setText("no specific data");
				CriticalNumberField.setText("no specific data");
				CasesPerOneMillionNumberField.setText("no specific data");

			} else {

				CasesNumberField.setText(Integer.toString(mq.getInfo("cases", output)));
				DeathsNumberField.setText(Integer.toString(mq.getInfo("deaths", output)));
				RecoveredNumberField.setText(Integer.toString(mq.getInfo("recovered", output)));
				ActiveNumberField.setText(Integer.toString(mq.getInfo("active", output)));
				TodayCasesNumberField.setText(Integer.toString(mq.getInfo("TodayCases", output)));
				TodayDeathsNumberField.setText(Integer.toString(mq.getInfo("TodayDeaths", output)));
				CriticalNumberField.setText(Integer.toString(mq.getInfo("Critical", output)));
				CasesPerOneMillionNumberField.setText(Integer.toString(mq.getInfo("CasesPerOneMillion", output)));
			}
		}
	}

	public void close() throws IOException {
		ConfirmExitBox exitBox = new ConfirmExitBox();
		boolean result = exitBox.display();

		if (result) {

			ScenebuilderMain.getWindow().close();
		}
	}

	public void onMouseDraggedBorderPane() {
//		bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//               ScenebuilderMain.getWindow().setX(event.getScreenX() - xOffset);
//               ScenebuilderMain.getWindow().setY(event.getScreenY() - yOffset);
//
//			ScenebuilderMain.getWindow().close();
//		}
//		});
	}
	public void onMousePressedBorderPane() {
//		bp.setOnMousePressed(new EventHandler<MouseEvent>() {
//	         @Override
//	         public void handle(MouseEvent event) {
//	             xOffset = event.getSceneX();
//	             yOffset = event.getSceneY();
//	         }
//	     });
	}

	public void minimize() {
		ScenebuilderMain.getWindow().setIconified(true);
	}

	

	public void addImages() throws SQLException {

		Image img;
		if (output.equals("World")) {
			img = new Image("/Images/world.png");

		} else {
			img = new Image("/Images/" + output + ".png");

		}
		ig.setImage(img);
		ig.setSmooth(true);
	}

	
	
}