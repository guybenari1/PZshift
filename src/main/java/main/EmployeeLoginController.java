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
        } catch (MongoException  err){
            System.err.println("Progarm ran into the error: " + err);
        }
        //just print here
        JOptionPane.showMessageDialog(null, "print phone book");
    }

    public void showHoursBTN (){
        try {
            DataBaseManager manager = DataBaseManager.getDBInstance();
            String result = manager.getHoursForEmp(manager.getUser());
            //display result.
        }catch (MongoException err){
            System.err.println("Program ran into an error: "+ err);
        }
        JOptionPane.showMessageDialog(null, "print hours");
    }

    public void nextWeekBTN (){

        JOptionPane.showMessageDialog(null, "print next week shift (if any)");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.nameT.setText("Hello, "+ "guy");
    }
}
