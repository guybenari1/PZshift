package main;

import com.mongodb.MongoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.DataBaseManager;
import model.Email;
import model.Manager;
import model.Worker;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewEmployeeController {
    @FXML
    private TextField nameTF = new TextField();
    @FXML
    private TextField phoneTf = new TextField();
    @FXML
    private TextField idTF = new TextField();
    @FXML
    private TextField emailTF = new TextField();
    @FXML
    private DatePicker birthdateDP = new DatePicker();
    @FXML
    private CheckBox managerCB = new CheckBox();

    @FXML
    void sendCodeBTN(ActionEvent event) {
       if(!managerCB.isSelected()){
           Worker temp = new Worker(nameTF.getText(),idTF.getText(),phoneTf.getText(),birthdateDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),emailTF.getText());
           workerProcess(event,temp);
       }
       Manager temp = new Manager(nameTF.getText(),idTF.getText(),phoneTf.getText(),birthdateDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),emailTF.getText());
       workerProcess(event,temp);
    }

    private void workerProcess(ActionEvent event,Worker temp){
        DataBaseManager manager = DataBaseManager.getDBInstance();
        boolean valWorker = temp.checkValid(temp);
        boolean existsAlready = manager.doesEmployeeExist(temp.getId());
        if(Email.checkValidEmail(emailTF) && valWorker && !existsAlready){
            try{
                manager.insertEmployee(temp);
            }catch (MongoException err){
                System.err.println("Program ran into error: " + err);
            }
            Email email = new Email(emailTF.getText());
            email.sendCodeByEmail(temp);
            JOptionPane.showMessageDialog(null, "Password sent to: "+emailTF.getText());
        }
        else {
            JOptionPane.showMessageDialog(null, "Sorry, We ran into a problem: ");
            if(!valWorker){
                JOptionPane.showMessageDialog(null, "Some data of the worker is invalid");
            }
            if(!Email.checkValidEmail(emailTF)){
                JOptionPane.showMessageDialog(null, "The email is not valid");
            }
            if(existsAlready){
                JOptionPane.showMessageDialog(null, "This worker is already employeed!");
            }
        }
    }

    private void sendMail(){

    }




}
