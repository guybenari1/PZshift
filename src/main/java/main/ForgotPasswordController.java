package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Email;
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ForgotPasswordController {
    @FXML
    private TextField emailTF = new TextField();

    @FXML
    void sendCodeBTN(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"New password sent to you");
        //check if email exist
    }

}
