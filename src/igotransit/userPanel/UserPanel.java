package igotransit.userPanel;

import igotransit.IGoTransit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import transitapp.CardHolder;

public class UserPanel {
	private static CardHolder cardHolder;
	
	public UserPanel(CardHolder cardHolderIn) {
		cardHolder = cardHolderIn;
	}
	
	public void loadPanel() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
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
    
    public static void setCardHolder(CardHolder cardHolderIn) {
    	cardHolder = cardHolderIn;
    }
    
    public static CardHolder getCardHolder() {
    	return cardHolder;
    }
}
