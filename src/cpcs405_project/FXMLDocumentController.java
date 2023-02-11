/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcs405_project;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {
    // This ID will be used for user sign in and games' records
    public static int id = -1;
    public static Stage alertStage;
    
    // These are ids for signing in 
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;
    // These are values for sign up
    @FXML
    private TextField signUpUsername;
    @FXML
    private TextField signUpPassword;
    @FXML
    private TextField signUpFirstName;
    @FXML
    private TextField signUpLastName;
    // This is the alert label
    

    @FXML
    private void goToRegister(ActionEvent event) throws IOException {
        changePage("SignUpPage.fxml");
    }

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        DBClass db = CPCS405_PROJECT.getDB();
        if (db == null) {
            alert("Check the connection of the database!");
            return; // Do not continue. the database object is empty
        }
        id = db.signin(textUsername.getText(), textPassword.getText());
        if (id != -1) {
            changePage("ChoicePage.fxml");
        }
        else {
            alert("Some error occured, make sure the enter valid data");
        }
    }

    @FXML
    private void signUp(ActionEvent event) throws IOException {
        DBClass db = CPCS405_PROJECT.getDB();
        if (db == null) {
            alert("Check the connection of the database!");
            return; // Do not continue. the database object is empty
        }
        boolean flag = db.SignUp(signUpUsername.getText(), signUpPassword.getText(), signUpFirstName.getText(), signUpLastName.getText());
        if (flag) {
            alert("You have signed up! now you can sign in");
        }
        else {
            alert("Some error occured, make sure that the password is 8 or more and change the username");
        }
    }

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        id = -1;
        changePage("WelcomePage.fxml");
    }

    @FXML
    private void goToChoice(ActionEvent event) throws IOException {
        changePage("ChoicePage.fxml");
    }

    @FXML
    private void goToGame(ActionEvent event) throws IOException {
        changePage("GamePage.fxml");
    }

    @FXML
    private void returnToSignIn(ActionEvent event) throws IOException {
        changePage("WelcomePage.fxml");
    }

    @FXML
    private void closeAlert(ActionEvent event) throws IOException {
        System.out.println("TestCloseAlert");
        alertStage.close();
    }

    private void changePage(String name) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        Stage stage = CPCS405_PROJECT.getStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void alert(String message) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Alert.fxml"));
        Scene scene = new Scene(root);
        alertStage = new Stage();
        alertStage.setTitle("Alert");
        alertStage.setScene(scene);
        alertStage.show();
        
        Label alertLabel = (Label) scene.lookup("#alertLabel");
        alertLabel.setText(message);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
