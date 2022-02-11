package mainAndControllers;

import com.mongodb.MongoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DataBaseManager;
import model.Worker;
import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeLoginController implements Initializable {
    @FXML
    private Text nameT = new Text();
    @FXML
    private TextArea weekShiftsTA= new TextArea();
    @FXML
    private TextArea managerMessagesTA= new TextArea();

    public void submitShiftsBTN(ActionEvent actionEvent){
        try {
            String pathName = "SubmitShifts.fxml";
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(pathName));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void phonebookBTN (){
        String s = Worker.getPhoneBook();
        JOptionPane.showMessageDialog(null, s);
    }

    public void showHoursBTN (){
        try {
            String result =  Worker.getHours();
            if (result == null){
                JOptionPane.showMessageDialog(null, "no hours yet");
            }
            else {
                JOptionPane.showMessageDialog(null, result);
            }
        }catch (MongoException err){
            System.err.println("Program ran into an error: "+ err);
        }
    }

    public void nextWeekBTN () {
        String res = Worker.getNextWeek();
        JOptionPane.showMessageDialog(null, res);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] firstName = DataBaseManager.getDBInstance().getUser().split(" ", 2);
        this.nameT.setText("Hello, "+ firstName[0]);
        ArrayList<String> messages = DataBaseManager.getDBInstance().getMessages();
        String allMessages = "";
        for (int i=0; i< messages.size(); i++){
            allMessages += messages.get(i);
        }
        this.managerMessagesTA.setText(allMessages);
        ArrayList<String> shifts = DataBaseManager.getDBInstance().getNextWeek(this.nameT.getText());
        if (shifts != null){
            String allShifts = "";
            for (int i=0; i< shifts.size(); i++){
                allShifts += shifts.get(i);
            }
            this.weekShiftsTA.setText(allShifts);
        }
    }
}
