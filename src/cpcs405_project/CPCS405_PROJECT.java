/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcs405_project;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class CPCS405_PROJECT extends Application {

    private static Stage currentStage;
    private static DBClass db;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        currentStage = stage;
        currentStage.setTitle("CPCS405 Project");
        stage.show();
        try {
            db = new DBClass();
            
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static Stage getStage() {
        return currentStage;
    }

    public static DBClass getDB() {
        return db;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
