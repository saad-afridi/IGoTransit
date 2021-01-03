/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igotransit.userPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Admin.Admin;
import LoadingData.Writing;
import igotransit.login.LoginPanel;
import igotransit.mainPanel.MainPanel;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import transitapp.Card;
import transitapp.CardHolder;
import transitapp.Route;
import transitapp.Tap;
import transitapp.TransitTime;
import transitapp.Trip;

/**
 * FXML Controller class
 *
 * @author Maor Gornic
 */
public class UserPanelController implements Initializable {

	private CardHolder cardHolder;
	private Admin admin;
	private Card selectedCard;
	private LoginPanel loginPanel;
	private ArrayList<Card> cards;
	private Card tripCard;
	private Timer timer;
	private TransitTime todaysDate;

	@FXML
	private Text costperMonth;

	@FXML
	private Text totalBalanceLbl;

	@FXML
	private Button finalizeTripBtn;

	@FXML
	private ListView<Tap> tapsLst;

	@FXML
	private TextField timeJumperTxt;

	@FXML
	private ComboBox<Card> tapCardSelect;

	@FXML
	private ImageView homeImg;

	@FXML
	private Text totalTripsLbl;

	@FXML
	private Label cardStatusLbl;

	@FXML
	private Label cardLbl;

	@FXML
	private Label profileNameLbl;

	@FXML
	private Label profileEmailLbl;

	@FXML
	private ListView<Card> cardsLst;

	@FXML
	private Text averageDistanceLbl;

	@FXML
	private Text dailyProfitLbl;

	@FXML
	private Text averageTripTime;

	@FXML
	private ListView<Trip> cardHolders;

	@FXML
	private Text nameLbl;

	@FXML
	private ImageView tripsBar;

	@FXML
	private ImageView manageBar;

	@FXML
	private ImageView tripPlannerBar;

	@FXML
	private ListView<Trip> lstTrips;

	@FXML
	private ListView<Trip> tripLogLst;

	@FXML
	private Text dailyTripsLbl;

	@FXML
	private ImageView homeBar;

	@FXML
	private ImageView settingstBar;

	@FXML
	private Label cardInfoLbl;

	@FXML
	private TabPane transitTab;

	@FXML
	private Text currTimeLbl;

	@FXML
	private Text currTimeLblTwo;

	@FXML
	private Button planningBtn;

	@FXML
	private Button addTapBtn;

	@FXML
	private Label cardBalanceLbl;

	@FXML
	private Label tripFeeLbl;

	@FXML
	private Label projectedBalanceLbl;

	@FXML
	private Label cardNumberLbl;

	@FXML
	private ComboBox<Route> routeComboBox;

	@FXML
	private ComboBox<String> tapLocationComboBox;

	@FXML
	private ComboBox<String> tapActionComboBox;

	@FXML
	private TitledPane cardInfoPane;

	@FXML
	void onHomePress(ActionEvent event) {
		transitTab.getSelectionModel().select(0);
		focus(0);
	}

	@FXML
	void onTripsPress(ActionEvent event) {
		transitTab.getSelectionModel().select(1);
		focus(1);
	}

	@FXML
	void onManagePress(ActionEvent event) {
		transitTab.getSelectionModel().select(2);
		focus(2);
	}

	@FXML
	void onTripPlannerPress(ActionEvent event) {
		transitTab.getSelectionModel().select(3);
		focus(3);
	}

	@FXML
	void onSettingsPress(ActionEvent event) {
		transitTab.getSelectionModel().select(4);
		focus(4);
	}

	@FXML
	void getCardHolderInfo(ActionEvent event) throws Exception {

		if (cardsLst.getItems().size() == 0) {
			System.out.println("The list is empty!");
		}

		else if (cardsLst.getSelectionModel().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select one of the above.", "iGoTransit - Alert",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			this.selectedCard = cardsLst.getSelectionModel().getSelectedItem();
			cardLbl.setText(this.selectedCard.getName());
			updateBalance();
		}

		if (this.selectedCard.isSuspended()) {
			updateCardStatus(1);
		} else {
			updateCardStatus(0);
		}

	}

	@FXML
	void add10(ActionEvent event) {
		if (canAddBalance()) {
			int result = JOptionPane.showConfirmDialog(null, "Would you like to proceed?",
					"iGoTransit - Balance Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (result == JOptionPane.YES_OPTION) {
				this.selectedCard.addBalance(10);
				cardLbl.setText(this.selectedCard.getName());
				cardInfoLbl.setText(String.valueOf(this.selectedCard.getBalance()));
				updateBalance();
			}
		}
	}

	@FXML
	void add20(ActionEvent event) {
		if (canAddBalance()) {
			int result = JOptionPane.showConfirmDialog(null, "Would you like to proceed?",
					"iGoTransit - Balance Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (result == JOptionPane.YES_OPTION) {
				this.selectedCard.addBalance(20);
				cardLbl.setText(this.selectedCard.getName());
				cardInfoLbl.setText(String.valueOf(this.selectedCard.getBalance()));
				updateBalance();
			}
		}

	}

	@FXML
	void add50(ActionEvent event) {
		if (canAddBalance()) {
			int result = JOptionPane.showConfirmDialog(null, "Would you like to proceed?",
					"iGoTransit - Balance Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (result == JOptionPane.YES_OPTION) {
				this.selectedCard.addBalance(50);
				cardLbl.setText(this.selectedCard.getName());
				cardInfoLbl.setText(String.valueOf(this.selectedCard.getBalance()));
				updateBalance();
			}
		}
	}

	@FXML
	void suspendCard(ActionEvent event) throws Exception {
		updateCardStatus(1);

	}

	private void updateCardStatus(int status) throws Exception {
		if (status == 0) {
			Writing.writeCardActions(this.selectedCard, "Unsuspend", 0, "\\src\\testing-files\\CardActions.txt");
			cardStatusLbl.setStyle("-fx-text-fill: #4FC282;");
			cardStatusLbl.setText("ACTIVE");
		} else {
			Writing.writeCardActions(this.selectedCard, "Suspend", 0, "\\src\\testing-files\\CardActions.txt");
			cardStatusLbl.setStyle("-fx-text-fill: #F04747;");
			cardStatusLbl.setText("SUSPENDED");
		}
	}

	@FXML
	void unsuspendCard(ActionEvent event) throws Exception {
		updateCardStatus(0);
	}

	@FXML
	void onHomePressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(0);
		focus(0);
	}

	@FXML
	void onTripsPressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(1);
		focus(1);
	}

	@FXML
	void onManagePressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(2);
		focus(2);
	}

	@FXML
	void onTripPlannerPressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(3);
		focus(3);
	}

	@FXML
	void onSettingsPressIcon(MouseEvent event) {
		transitTab.getSelectionModel().select(4);
		focus(4);
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
	void deleteCard(ActionEvent event) throws Exception {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to do this?",
				"iGoTransit - Balance Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			if (cardsLst.getItems().size() > 1) {
				this.cardHolder.removeCard(this.selectedCard);
				cardsLst.getItems().clear();
				cardsLst.getItems().addAll(this.cardHolder.getCards());
				Writing.writeCardActions(this.selectedCard, "RemoveCard", 0, "\\src\\testing-files\\CardActions.txt");
			} else {
				JOptionPane.showMessageDialog(null, "Having 0 cards is not allowed!", "iGoTransit - Card",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	@FXML
	void addCard(ActionEvent event) throws Exception {
		String newCardName = JOptionPane.showInputDialog(null, "Enter the number of your card", "Card Number",
				JOptionPane.INFORMATION_MESSAGE);
		Card card = new Card(newCardName, this.cardHolder.getName());
		this.cardHolder.addCard(card);
		cardsLst.getItems().add(card);
		Writing.writeCardActions(card, "AddCard", 0, "\\src\\testing-files\\CardActions.txt");
	}

	@FXML
	void startPlanningBtn() {
		if (tapCardSelect.getSelectionModel().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select a card!", "iGoTransit - No Card Selected",
					JOptionPane.ERROR_MESSAGE);
		} else {
			this.tripCard = tapCardSelect.getSelectionModel().getSelectedItem();
			if (this.tripCard.isSuspended()) {
				JOptionPane.showMessageDialog(null, "Card is suspended! Please choose another card!", "iGoTransit - Suspended Card",
						JOptionPane.ERROR_MESSAGE);
			} else {
				planningBtn.setDisable(true);
				tapCardSelect.setDisable(true);
				routeComboBox.setDisable(false);
				cardNumberLbl.setText(this.tripCard.getName());
				cardBalanceLbl.setText("$" + this.tripCard.getBalance());
				tripFeeLbl.setText("$0");
				projectedBalanceLbl.setText("$" + this.tripCard.getBalance());
				finalizeTripBtn.setDisable(false);
			}
		}
	}

	@FXML
	void onSelectRouteName(ActionEvent event) {
		tapLocationComboBox.setDisable(false);
		addTapBtn.setDisable(true);
		tapLocationComboBox.getItems().clear();
		tapActionComboBox.getItems().clear();
		if (!routeComboBox.getSelectionModel().isEmpty()) {
			for (String str : routeComboBox.getSelectionModel().getSelectedItem().getStops()) {
				tapLocationComboBox.getItems().add(str);
			}
		}
	}

	@FXML
	void onSelectTapLocation(ActionEvent event) {
		tapActionComboBox.getItems().clear();
		tapActionComboBox.getItems().add("IN");
		tapActionComboBox.getItems().add("OUT");
		tapActionComboBox.setDisable(false);
	}

	@FXML
	void onSelectTapAction(ActionEvent event) {
		addTapBtn.setDisable(false);
	}

	@FXML
	void timeJump(ActionEvent event) {
		try {
			int min = Integer.parseInt(timeJumperTxt.getText());
			this.todaysDate.addMinutes(min);
			currTimeLbl.setText(todaysDate.getFormattedTime());
			currTimeLblTwo.setText(todaysDate.getFormattedTime());
			JOptionPane.showMessageDialog(null, min + " min(s) were added successfuly!", "iGoTransit - Integer Needed",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Needs to be an integer", "iGoTransit - Integer Needed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	void onAddTap(ActionEvent event) {
		String action = tapActionComboBox.getSelectionModel().getSelectedItem();
		String routeName = routeComboBox.getSelectionModel().getSelectedItem().getName();
		String station = tapLocationComboBox.getSelectionModel().getSelectedItem();
		String transportation = routeComboBox.getSelectionModel().getSelectedItem().getTransportation();

		ArrayList<Tap> taps = new ArrayList<Tap>();
		for (Tap tap : tapsLst.getItems()) {
			taps.add(tap);
		}

		ArrayList<Trip> trips = admin.getTrips(this.tripCard, taps, admin.getTransitSystem());
		double fee = 0;
		for (Trip trip : trips) {
			fee = fee + trip.getTripFee();
		}

		if ((transportation.equals("bus") && action.equals("IN")) && this.tripCard.getBalance() - fee < 2) {
			JOptionPane.showMessageDialog(null, "Not enough balance", "iGoTransit - Balance Not Enough",
					JOptionPane.ERROR_MESSAGE);
		} else if ((transportation.equals("subway") && action.equals("IN")) && this.tripCard.getBalance() - fee < 0) {
			JOptionPane.showMessageDialog(null, "Not enough balance", "iGoTransit - Balance Not Enough",
					JOptionPane.ERROR_MESSAGE);
		} else {
			TransitTime tmpTime = this.todaysDate.clone();
			Tap tap = new Tap(action, station, transportation, tmpTime, routeName);
			tapsLst.getItems().add(tap);
			taps.add(tap);
			ArrayList<Trip> trips2 = admin.getTrips(this.tripCard, taps, admin.getTransitSystem());
			double fee2 = 0;
			for (Trip trip : trips2) {
				fee2 = fee2 + trip.getTripFee();
			}
			tripFeeLbl.setText("$" + fee2);
			projectedBalanceLbl.setText("$" + (this.tripCard.getBalance() - fee2));

		}
	}

	@FXML
	void finalizeTripBtn() throws IOException {
		ArrayList<Tap> taps = new ArrayList<Tap>();
		for (Tap tap : tapsLst.getItems()) {
			taps.add(tap);
		}

		Writing.writeTaps(this.tripCard, taps, "\\src\\testing-files\\Trips2.txt");

		this.admin = new Admin();
		this.cardHolder = this.admin.getCardHolder(this.cardHolder.getName());

		tripLogLst.getItems().clear();
		for (Trip trip : cardHolder.getRecentTrip()) {
			tripLogLst.getItems().add(trip);
		}

		averageTripTime.setText(cardHolder.avgTripTime() + "m");

		int count = 0;
		for (Trip trip : cardHolder.getTrips()) {
			count++;
		}
		totalTripsLbl.setText(count + "");

		averageDistanceLbl.setText(String.valueOf((cardHolder.totalTripLength() / count)));

		double balance = 0;
		for (Card card : cardHolder.getCards()) {
			balance = balance + card.getBalance();
		}
		totalBalanceLbl.setText("$ " + balance);

		ArrayList<Trip> trips = cardHolder.getTrips();
		lstTrips.getItems().clear();
		for (Trip trip : trips) {
			lstTrips.getItems().add(trip);
		}

		this.updateBalance();
		tapsLst.getItems().clear();
		finalizeTripBtn.setDisable(true);
		tapActionComboBox.setDisable(true);
		tapLocationComboBox.setDisable(true);
		routeComboBox.setDisable(true);
		tapCardSelect.setDisable(false);
		planningBtn.setDisable(false);
		addTapBtn.setDisable(true);

	}

	@FXML
	void onNameChangeAction(ActionEvent event) {
		String newName = JOptionPane.showInputDialog("What name would you like to use?");
		if (newName.isEmpty() || newName == null) {
			JOptionPane.showMessageDialog(null, "Please enter a valid name!", "iGoTransit - Invalid Name",
					JOptionPane.ERROR_MESSAGE);
		} else {
			this.cardHolder.changeName(newName);
			nameLbl.setText("User: " + newName);
			profileNameLbl.setText(newName);
		}
	}

	/**
	 * Highlights the selected
	 * 
	 * @param index
	 */
	private void focus(int index) {
		homeBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		tripsBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		manageBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		tripPlannerBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));
		settingstBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state1.png"));

		if (index == 0)
			homeBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 1)
			tripsBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 2)
			manageBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 3)
			tripPlannerBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
		if (index == 4)
			settingstBar.setImage(new Image("\\igotransit\\assets\\selection-bar-state2.png"));
	}

	/**
	 * Returns true iff the card is not suspended and its balance could be updated.
	 * 
	 * @return whether the card is not suspended and its balance could be updated.
	 */
	private boolean canAddBalance() {
		if (this.selectedCard.isSuspended()) {
			JOptionPane.showMessageDialog(null,
					"The selected card is suspended! Please unsuspend the card, and try again!",
					"iGoTransit - Card Suspended", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void updateBalance() {
		cardsLst.getItems().clear();
		cardInfoLbl.setText(String.valueOf(this.selectedCard.getBalance()));
		for (Card card : this.cards) {
			cardsLst.getItems().add(card);
		}
		cardInfoLbl.setText("$" + cardInfoLbl.getText());
	}

	private void loadAll() {
		this.cardHolder = UserPanel.getCardHolder();
		this.admin = MainPanel.getAdmin();
		nameLbl.setText("User " + this.cardHolder.getName());
		ArrayList<Trip> trips = cardHolder.getTrips();
		for (Trip trip : trips) {
			lstTrips.getItems().add(trip);
		}

		cards = cardHolder.getCards();
		this.selectedCard = cards.get(0);

		if (this.selectedCard.isSuspended()) {
			cardStatusLbl.setStyle("-fx-text-fill: #F04747;");
			cardStatusLbl.setText("SUSPENDED");
		} else {
			cardStatusLbl.setStyle("-fx-text-fill: #4FC282;");
			cardStatusLbl.setText("ACTIVE");
		}

		cardLbl.setText(this.selectedCard.getName());

		updateBalance();
		for (Trip trip : cardHolder.getRecentTrip()) {
			tripLogLst.getItems().add(trip);
		}

		averageTripTime.setText(cardHolder.avgTripTime() + "m");

		costperMonth.setText("$" + cardHolder.monthTripCost());

		int count = 0;
		for (Trip trip : cardHolder.getTrips()) {
			count++;
		}
		totalTripsLbl.setText(count + "");
		profileNameLbl.setText(this.cardHolder.getName());
		profileEmailLbl.setText(this.cardHolder.getEmail());

		averageDistanceLbl.setText(String.valueOf((cardHolder.totalTripLength() / count)));

		double balance = 0;
		for (Card card : cardHolder.getCards()) {
			balance = balance + card.getBalance();
		}
		totalBalanceLbl.setText("$ " + balance);

		tapCardSelect.getItems().addAll(cardHolder.getCards());

		for (Route route : admin.getTransitSystem().getBusRoutes()) {
			routeComboBox.getItems().add(route);
		}

		for (Route route : admin.getTransitSystem().getSubwaysRoutes()) {
			routeComboBox.getItems().add(route);
		}

	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadAll();
		TransitTime todaysDate = admin.lastDate();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				todaysDate.addMinutes(1);
				currTimeLbl.setText(todaysDate.getFormattedTime());
				currTimeLblTwo.setText(todaysDate.getFormattedTime());
			}
		}, 0, 60000);
		this.todaysDate = todaysDate;
	}

}
