/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igotransit.login;

import igotransit.mainPanel.MainPanel;
import igotransit.userPanel.UserPanel;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Admin.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import transitapp.Card;
import transitapp.CardHolder;
import transitapp.Main;
import transitapp.Tap;
import transitapp.TransitSystem;
import transitapp.Trip;

/**
 * FXML Controller class
 *
 * @author Maor Gornic
 */
public class LoginPanelController implements Initializable {

	private Admin admin;

	private ArrayList<CardHolder> cardHolders;

	@FXML
	private Button btnLogin;

	@FXML
	private ImageView btnGoogle;

	@FXML
	private Label btnForgotPass;

	@FXML
	private TextField emailTxt;

	@FXML
	private PasswordField txtPassword;

	@FXML
	void onMouseHover(MouseEvent event) {
		btnLogin.setStyle("-fx-background-color: #FFC107;");
	}

	@FXML
	void onMouseExit(MouseEvent event) {
		btnLogin.setStyle("-fx-background-color: #6E99D9;");
	}

	@FXML
	void handleButtonAction(ActionEvent event) throws Exception {
		CardHolder cardHolder = validateCardHolderLogin(emailTxt.getText(), txtPassword.getText());
		MainPanel mainPanel = new MainPanel(this.admin);
		UserPanel userPanel = new UserPanel(cardHolder);

		if (cardHolder != null) {
			userPanel.loadPanel();
			
		} else if (emailTxt.getText().equals("admin") && 
				 txtPassword.getText().equals("admin")) {
			mainPanel.loadMainPanel();
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect username or password!", "iGoTransit - Incorrect Login",
					JOptionPane.ERROR_MESSAGE);
			emailTxt.setText("");
			txtPassword.setText("");			
		}
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.admin = new Admin();
		this.cardHolders = admin.getCardHolders();
	}

	/**
	 * Returns true iff the user name and password given by the user match the name
	 * of a card in all the card holders in this transit system.
	 * 
	 * @param userName of this card holder.
	 * @param password of this card holder.
	 * @return whether the user name and password given by the user match the name
	 *         of a card in all the card holders in this transit system.
	 */
	private CardHolder validateCardHolderLogin(String userName, String password) {
		if (userName.equals(password)) {
			for (CardHolder cardHolder : this.cardHolders) {
				if (cardHolder.getName().equals(userName)) {
					return cardHolder;
				}
			}
		}
		return null;
	}

//	private String validateLogin() {
//		if (emailTxt.getText().equals("admin") && txtPassword.getText().equals("admin")) {
//			System.out.println("LOGIN CONFIRMED!");
//			return "admin";
//		}
//
//		else if (validateCardHolder(emailTxt.getText(), txtPassword.getText()) != null) {
//			return "user";
//		}
//
////        else if (emailTxt.getText().equals("user")
////                && txtPassword.getText().equals("user")) {
////            System.out.println("USER LOGIN!");
////            return "user";
////        }
//
//		return "";
//	}
}
