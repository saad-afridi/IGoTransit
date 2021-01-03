/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igotransit.login;

import igotransit.IGoTransit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Maor Gornic
 */
public class LoginPanel {
    /**
     * Returns a Parent object of the login panel.
     * 
     * @return Parent object containing Login Panel FXML.
     * @throws Exception 
     */
    public Parent getRoot() throws Exception {
        return FXMLLoader.load(getClass().getResource("LoginPanel.fxml"));
    }
    
    /**
     * Changes the main stage's scene to be the Login Panel.
     * 
     * @throws Exception
     */
    public void loadLogInPanel() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("LoginPanel.fxml"));
		Scene scene = new Scene(root);
		Stage mainStage = IGoTransit.getMainStage();
		mainStage.setScene(scene);
		mainStage.centerOnScreen();
	}
}
