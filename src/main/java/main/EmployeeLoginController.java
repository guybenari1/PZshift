package main;

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
        try {
            DataBaseManager manager = DataBaseManager.getDBInstance();
            ArrayList<String> phoneBook = manager.getPhoneBook();
            String contacts = "";
            for (String s : phoneBook) {
                contacts += s+"\n";
            }
            JOptionPane.showMessageDialog(null, contacts);
        } catch (MongoException  err){
            System.err.println("Progarm ran into the error: " + err);
        }

    }

    public void showHoursBTN (){
        try {
            DataBaseManager manager = DataBaseManager.getDBInstance();
            String result = manager.getHoursForEmp(manager.getUser());
            JOptionPane.showMessageDialog(null, result);

        }catch (MongoException err){
            System.err.println("Program ran into an error: "+ err);
        }
        JOptionPane.showMessageDialog(null, "print hours");
    }

    public void nextWeekBTN () {
        if (!DataBaseManager.getDBInstance().nextWeekExists()) {
            JOptionPane.showMessageDialog(null, "there are no weeks for next week yet");
        } else {
            try {
                DataBaseManager manager = DataBaseManager.getDBInstance();
                ArrayList<String> toDisplay = manager.getNextWeek(manager.getUser());
                String shifts = "";
                for (String s : toDisplay) {
                    shifts += s + "\n";
                }
                JOptionPane.showMessageDialog(null, shifts);
            } catch (MongoException err) {
                System.err.println("System ran into an error " + err);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] firstName = DataBaseManager.getDBInstance().getUser().split(" ", 2);
        this.nameT.setText("Hello, "+ firstName[0]);
    }
}
