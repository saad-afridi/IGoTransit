/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igotransit;

import igotransit.login.LoginPanel;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Maor Gornic
 */
public class IGoTransit extends Application {
    private static Stage mainStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        LoginPanel loginPanel = new LoginPanel();
        Parent root = loginPanel.getRoot();
        Scene scene = new Scene(root);
        stage.setTitle("iGoTransit");
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("\\igotransit\\assets\\ground_transportation.png"));
        stage.setResizable(false);
        mainStage = stage;
        stage.show();
    }
    
    /**
     * Returns the main stage of this application.
     * 
     * @return the main stage of this application.
     */
    public static Stage getMainStage() {
        return mainStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
