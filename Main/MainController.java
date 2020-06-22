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
	private Label headline;

	// -----------------------------------------------
	private String value = "";
	private String output = "world";

	// --------------------------------------------------
	
	private double xOffset = 0;
	private double yOffset = 0;
	//-----------------------------------------------------
	@FXML
	private BorderPane bp = new BorderPane();
	//-------------------------------------------------------

	private ScenebuilderMain gui = new ScenebuilderMain();

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		System.out.println("i should be there");
		try {
			addList(); // fügt die ListView mit Ländernamen und infiziertenzahlen ein
			addButtons(); // fügt die Buttons zur ArrayList hinu
			addTextContent();
			
			
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

		for (Button b : chartButtons) {
			b.setOnAction(e -> {

				value = b.getText(); // übergeben des Button Texts für den Graphen
				try {
					infoChart(value); // zeichnen vom Graphen 'value'

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
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

	public void infoChart(String value) throws SQLException, ClassNotFoundException {

		mq = new Mysql();
		lineChart.setAnimated(false);
		lineChart.getData().clear();
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		ArrayList<Long> date = mq.getDate(output);
		ArrayList<String> dateX = new ArrayList<String>();
		chartButtons = new ArrayList<Button>();
		ArrayList<Integer> info = mq.getInfo(value, output);
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

	public void addTextContent() throws SQLException {

		totalConfirmed.setText("Total Confirmed");

		int totalConfirmed = mq.totalInfo("cases");
		totalConfirmedNumber.setText(Integer.toString(totalConfirmed));
	}

	public void close() throws IOException {
		ConfirmExitBox exitBox = new ConfirmExitBox();
		boolean result = exitBox.display();

		if (result) {

			ScenebuilderMain.getWindow().close();
		}
	}

	
	public void onMouseDraggedBorderPane()
	{
		bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               ScenebuilderMain.getWindow().setX(event.getScreenX() - xOffset);
               ScenebuilderMain.getWindow().setY(event.getScreenY() - yOffset);

			ScenebuilderMain.getWindow().close();
		}
		
	}

	public void minimize() {
		gui.getWindow().hide();
	}
	
	public void onMousePressedBorderPane()
	{
		bp.setOnMousePressed(new EventHandler<MouseEvent>() {
	         @Override
	         public void handle(MouseEvent event) {
	             xOffset = event.getSceneX();
	             yOffset = event.getSceneY();
	         }
	     });
	}
	
	 
	

}