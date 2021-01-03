/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igotransit.mainPanel;

import Admin.Admin;
import igotransit.IGoTransit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Maor Gornic
 */
public class MainPanel {
	private static Admin admin;

	public MainPanel(Admin adminIn) {
		admin = adminIn;
	}

	public void loadMainPanel() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainPanel.fxml"));
		Scene scene = new Scene(root);
		Stage mainStage = IGoTransit.getMainStage();
		mainStage.setScene(scene);
		mainStage.centerOnScreen();
	}

	/**
	 * Returns a Parent object of the main panel.
	 * 
	 * @return Parent object containing the main panel FXML object.
	 * @throws Exception
	 */
	public Parent getRoot() throws Exception {
		return FXMLLoader.load(getClass().getResource("MainPanel.fxml"));
	}
	
	public static void setAdmin(Admin adminIn) {
    	admin = adminIn;
    }
    
    public static Admin getAdmin() {
    	return admin;
    }
}
