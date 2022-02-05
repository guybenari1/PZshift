package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Email;

import javax.swing.*;
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
        if(checkValidEmail(emailTF)){
            Email email = new Email(emailTF.getText());
            email.sendCodeByEmail();
            JOptionPane.showMessageDialog(null, "Password sent to: "+emailTF.getText());

        }
        else {
            JOptionPane.showMessageDialog(null, "Sorry, invalid email. try again");
        }
    }

    private boolean checkValidEmail(TextField email){
        //check if email is in the users DB in another method
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email.getText());
        return mat.matches();
    }
}
