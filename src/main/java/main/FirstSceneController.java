package main;

import com.mongodb.MongoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DataBaseManager;

public class FirstSceneController {
    @FXML
    private TextField emailTF = new TextField();
    @FXML
    private TextField passwordTF = new TextField();



    public void forgotPassBTN(ActionEvent actionEvent){
        try {
            String pathName = "ForgotPassword.fxml";
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
    public void loginBTN(ActionEvent actionEvent){
        try{
            DataBaseManager manager = DataBaseManager.getDBInstance();
            boolean valid = manager.validLogin(emailTF.getText(),passwordTF.getText());
            if(!valid){
                //show incorrect entry
            }
            manager.changeUser(manager.getEmployeeName(emailTF.getText()));
            valid = manager.isManager(emailTF.getText());
            if(!valid){
                employeeLogin(actionEvent);
            }
            managerLogin(actionEvent);
        }catch (MongoException err){
            System.err.println("Progarm ran into the error: " + err);
        }

    }


    public void managerLogin(ActionEvent actionEvent){
        try {
            String pathName = "ManagerLogin.fxml";
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
    public void employeeLogin(ActionEvent actionEvent){
        try {
            String pathName = "EmployeeLogin.fxml";
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
}
