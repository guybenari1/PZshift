package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Email;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class sendCodeToEmail {
    @FXML
    private TextField newEmail = new TextField();
    @FXML
    private TextField forgotPass = new TextField();

    @FXML
    void btnSendCode(ActionEvent event) {
        if(checkValidEmail(newEmail)){
            Email email = new Email(newEmail.getText());
            email.sendCodeByEmail();
            JOptionPane.showMessageDialog(null, "Password sent to: "+newEmail.getText());

        }
        else {
            JOptionPane.showMessageDialog(null, "Sorry, invalid email. try again");
        }
    }
    @FXML
    void btnForgotCode(ActionEvent event) {
        if(checkValidEmail(newEmail)){
            //check if email exist
            JOptionPane.showMessageDialog(null, "Password sent to: "+newEmail.getText());

        }
        else {
            //check if email exist
            JOptionPane.showMessageDialog(null, "Sorry, email address doesnt exsict. try again");
        }    }

    private boolean checkValidEmail(TextField email){
        //check if email is in the users DB in another method
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email.getText());
        return mat.matches();

    }

}
