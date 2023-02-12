package cpcs405_project;

import cpcs405_project.CPCS405_PROJECT;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class DyscalculiaGUIController implements Initializable {
    @FXML
    Label point, mistake, round, equation, msg;
    @FXML
    Button next;
    @FXML
    TextField ans;

    Media correctSound, worngSound;

    MediaPlayer right, wrong;

    static int lvl = 1;

    int pts = 0, mis = 0, rounds = 1, right_answer;

    long start, end;

    int[] point_limit = {10, 100, 1000};
    int[] limit_rounds = {5, 10, 15};
    char[] operator = {'-', '+'};

    //=======================================================================================================
    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChoicePage.fxml"));
        Stage stage = CPCS405_PROJECT.getStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //=======================================================================================================
    public void getEquation() {//make random equation
        Random rand = new Random();

        //right and left numbers, and the operation
        int right = rand.nextInt(point_limit[lvl - 1]);
        int left = rand.nextInt(point_limit[lvl - 1]);
        char op = operator[rand.nextInt(2)];

        //save answer
        right_answer = (op == '-' ? right - left : right + left);

        //print equation
        equation.setText("" + right + op + left);
    }

    //========================================================================================================
    @FXML
    public void ansButton(ActionEvent event) throws IOException, InterruptedException {
        if(next.getText().equals("Exit"))
            System.exit(0);
        
        
        DBClass db = CPCS405_PROJECT.getDB();
        String currentEquation = equation.getText();
        int firstNumber = Integer.parseInt(currentEquation.charAt(0) + ""); // Make it a string, get the first number
        int secondNumber = Integer.parseInt(currentEquation.charAt(2) + ""); // Make it a string, get the second number
        String opeator = currentEquation.charAt(1) + "" ; // get the operator
        //check the answer
        

        if (next.getText().equals("Answer")) {//answer mode
            //check if textField not empty
            if (ans.getText().equals("")) {//answer empty
                msg.setText("Please Enter An Answer!!");
                return;
            }

            //check if that answer is a number
            try {
                int user_answer = Integer.parseInt(ans.getText());

                //check answer
                if (right_answer == user_answer) {//correct
                    point.setText("POINTS: " + (++pts));//update points
                    msg.setText("CORRECT");
                    db.InsertGameRecord(firstNumber, secondNumber, opeator, 1, FXMLDocumentController.id); // Game number is 1
                    
                } else {//wrong
                    mistake.setText("MISS: " + (++mis));//update points
                    msg.setText("WRONG. Correct Answer Is: " + right_answer);
                }

            } catch (Exception e) {
                msg.setText("Please Enter A Number!!");
                return;
            }

        } else {//change the round mode
            if (rounds++ == limit_rounds[lvl - 1]) {//finished number of rounds
                end = System.currentTimeMillis();//timer end
                equation.setText("Game Over ");
                next.setText("Exit");
                
                return ;

            } else {

                //update the rounds
                round.setText("ROUNDS: " + rounds);

                //clear the text
                msg.setText("");
                ans.clear();

                //update the equation
                getEquation();
            }
        }

        changeText();

    }

    //==========================================================================================================
    public void changeText() {
        if (next.getText().equals("Answer")) {
            next.setText("Ok");
        } else {
            next.setText("Answer");
        }
    }
    //===========================================================================================================

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start = System.currentTimeMillis();//start timer
        point.setText("POINTS: " + pts);
        mistake.setText("MISS: " + mis);
        round.setText("ROUNDS: " + rounds);

        equation.setAlignment(Pos.CENTER);
        msg.setAlignment(Pos.CENTER);
        next.setAlignment(Pos.CENTER);

        getEquation();
        start = System.currentTimeMillis();//start timer
    }

}
