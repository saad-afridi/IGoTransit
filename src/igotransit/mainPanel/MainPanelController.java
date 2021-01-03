/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igotransit.mainPanel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import Admin.Admin;
import igotransit.login.LoginPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import transitapp.Card;
import transitapp.CardHolder;
import transitapp.TransitTime;
import transitapp.Trip;

/**
 * FXML Controller class
 *
 * @author Maor Gornic, Honglei Song
 */
public class MainPanelController implements Initializable {

	private Admin admin;
	
	private LoginPanel loginPanel;

	@FXML
	private Label cardInfoLbl;

	@FXML
	private ListView<Trip> tripLogLst;

	@FXML
	private ListView<Card> cardsLst;

	@FXML
	private ListView<Trip> recentTripsLst;

	@FXML
	private ListView<CardHolder> cardHolders;

	@FXML
	private Text dailyTripsLbl;

	@FXML
	private ListView<String> lstTrips;

	@FXML
	private ImageView homeBar;

	@FXML
	private TabPane transitTab;

	@FXML
	private Text averageDistanceLbl;

	@FXML
	private Text dailyProfitLbl;

	@FXML
	private DatePicker datePicker;

	@FXML
	private ImageView calendarBar;

	@FXML
	private Text averageTripTime;

	@FXML
	private ImageView cardHoldersBar;

	@FXML
	private ImageView aboutUsBar;

	@FXML
	private PieChart transitChart;

	@FXML
	private Text currTimeLbl;

	@FXML
	void onHomePress(ActionEvent event) {
		transitTab.getSelectionModel().select(0);
		focus(0);
	}

	@FXML
	void onCalendarPress(ActionEvent event) {
		transitTab.getSelectionModel().select(1);
		focus(1);
	}

	@FXML
	void onCardHoldersPress(ActionEvent event) {
		transitTab.getSelectionModel().select(2);
		focus(2);
	}

	@FXML
	void onAboutUsPress(ActionEvent event) {
		transitTab.getSelectionModel().select(3);
		focus(3);
	}

	@FXML
	void onHomePressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(0);
		focus(0);
	}

	@FXML
	void onCalendarPressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(1);
		focus(1);
	}

	@FXML
	void onCardHoldersPressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(2);
		focus(2);
	}

	@FXML
	void onAboutUsPressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(3);
		focus(3);
	}
	
	@FXML
    void onSignOutPressIcon(MouseEvent event) throws Exception {
		loginPanel = new LoginPanel();
		loginPanel.loadLogInPanel();
    }

    @FXML
    void onSignOutPress(ActionEvent event) throws Exception {
		loginPanel = new LoginPanel();
		loginPanel.loadLogInPanel();
    }
    

	@FXML
	void findTripsonDate(ActionEvent event) {
		lstTrips.getItems().clear();
		LocalDate date = datePicker.getValue();
		int year = date.getYear();
		String monthStr = String.valueOf(date.getMonthValue());
		String dateStr = String.valueOf(date.getDayOfMonth());
		if (monthStr.length() < 2) {
			monthStr = "0" + monthStr;
		}
		if (dateStr.length() < 2) {
			dateStr = "0" + dateStr;
		}
		String date_str = (year + ":" + monthStr + ":" + dateStr + ":00:00");
		TransitTime date_transit = new TransitTime(date_str);

		ArrayList<Trip> trips_arr = admin.todaysTrips(date_transit);

		for (Trip trip : trips_arr) {
			lstTrips.getItems().add(trip.toString());
		}

	}

	@FXML
	void getCardHolderInfo(ActionEvent event) {
		if (cardHolders.getItems().size() == 0) {
			System.out.println("The list is empty!");
		}

		else if (cardHolders.getSelectionModel().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select one of the above.", "iGoTransit - Alert",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			CardHolder cardHolder = cardHolders.getSelectionModel().getSelectedItem();
			recentTripsLst.getItems().clear();
			cardsLst.getItems().clear();

			System.out.println(cardHolder.toString());
			for (Trip trip : cardHolder.getRecentTrip()) {
				recentTripsLst.getItems().add(trip);
			}

			for (Card card : cardHolder.getCards()) {
				cardsLst.getItems().add(card);
			}
		}
	}

	void getCardInfo(ActionEvent event) {
		Card card = cardsLst.getSelectionModel().getSelectedItem();
		cardInfoLbl.setText(card.toString());
	}

	@FXML
	void onSignInHover(MouseEvent event) {
		System.out.println("hover over");
	}

	@FXML
	void onSignInHoverExit(MouseEvent event) {
		System.out.println("exit");
	}

	/**
	 * Highlights the selected
	 * 
	 * @param index
	 */
	private void focus(int index) {
		homeBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		calendarBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		cardHoldersBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		aboutUsBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));

		if (index == 0)
			homeBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 1)
			calendarBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 2)
			cardHoldersBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 3)
			aboutUsBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
	}

	/**
	 * 
	 */
	private void loadTripLogs() {
		if (tripLogLst.getItems().size() > 0) {
			tripLogLst.getItems().clear();
		}

		ArrayList<Trip> tripLog = admin.todaysTrips(admin.lastDate());
		for (Trip tmpTrip : tripLog) {
			tripLogLst.getItems().add(tmpTrip);
		}
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		this.admin = MainPanel.getAdmin();
		ArrayList<CardHolder> cardHolderArr = admin.getCardHolders();

		for (CardHolder cardholder : cardHolderArr) {
			cardHolders.getItems().add(cardholder);
		}

		loadTripLogs();

		averageTripTime.setText(String.valueOf(admin.averageTime()) + "m");
		averageDistanceLbl.setText(String.valueOf(admin.averageDistance()));
		dailyTripsLbl.setText(String.valueOf(admin.numTripsLastDay()));
		dailyProfitLbl.setText("$" + admin.lastDayProfits());

		TransitTime todaysDate = admin.lastDate();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				todaysDate.addMinutes(1);
				currTimeLbl.setText(todaysDate.getFormattedTime());
			}
		}, 0, 60000);

		datePicker.setValue(admin.lastDate().getTime().toLocalDate());
		int totalBusRides = admin.getTotalRides("bus");
		int totalSubwayRides = admin.getTotalRides("subway");

		ObservableList<PieChart.Data> transitTrips = FXCollections.observableArrayList(
				new PieChart.Data("Bus Rides (" + totalBusRides + ")", totalBusRides),
				new PieChart.Data("Subway Rides (" + totalSubwayRides + ")", totalSubwayRides));
		transitChart.setData(transitTrips);
	}

}