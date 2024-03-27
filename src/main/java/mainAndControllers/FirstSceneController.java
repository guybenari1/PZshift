package mainAndControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LoginMaster;
import javax.swing.*;

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
        String s = LoginMaster.getInstance().loginB(emailTF.getText(),passwordTF.getText());
        if(s.equals("employee")){
            employeeLogin(actionEvent);
        } else if(s.equals("manager")){
            managerLogin(actionEvent);
        } else {
            JOptionPane.showMessageDialog(null, s);
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
