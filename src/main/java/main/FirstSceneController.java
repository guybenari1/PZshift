package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        //check if manager or employee
        employeeLogin(actionEvent);
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
