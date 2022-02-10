package main;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.DataBaseManager;
import model.Email;
import model.LoginMaster;
import model.Worker;
import org.bson.Document;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ForgotPasswordController {
    @FXML
    private TextField emailTF = new TextField();

    @FXML
    void sendCodeBTN(ActionEvent event) {
        String res = LoginMaster.getInstance().forgotPassword(emailTF.getText());
        JOptionPane.showMessageDialog(null,res);
    }

}
