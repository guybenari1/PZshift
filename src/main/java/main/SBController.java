package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SBController {
    //add new emails for managers
    public void pressSendCode(ActionEvent actionEvent){
        System.out.println("code sent in email");
    }
    public void pressAddEmpl(ActionEvent actionEvent){
        try {
            String pathName = "main/addEmp.fxml";
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
    public void pressForgot(ActionEvent actionEvent){
        try {
            String pathName = "main/forgotPass.fxml";
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
    public void pressLogIn(ActionEvent actionEvent){
        //check if manager or employee
        pressEmpLogin(actionEvent);
    }
    public void pressMangerLogin(ActionEvent actionEvent){
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
    public void pressEmpLogin(ActionEvent actionEvent){
        try {
            String pathName = "main/login.fxml";
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
