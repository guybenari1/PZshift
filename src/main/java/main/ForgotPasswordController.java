package main;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.DataBaseManager;
import model.Email;
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
        try{
            DataBaseManager manager =DataBaseManager.getDBInstance();
            String s = manager.getEmployeeName(emailTF.getText());
            if(s==null){
                //doesn't exist in system
            }
            Email email = new Email(emailTF.getText());
            Worker temp=new Worker(emailTF.getText());
            email.sendCodeByEmail(temp);
            manager.updatePassword(s,temp.getPassword());
        }catch (MongoException err){
            System.err.println("System ran into an error: " +err);
        }
        JOptionPane.showMessageDialog(null,"New password sent to you");
        //actually  mail password
    }

}
