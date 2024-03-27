package mainAndControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DataBaseManager;
import model.Manager;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerLoginController implements Initializable {
    @FXML
    private Text nameT = new Text();

    public void messagesBTN(ActionEvent actionEvent){
        try {
            String pathName = "Messages.fxml";
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

    public void addEmployeeBTN(ActionEvent actionEvent){
        try {
            String pathName = "AddNewEmployee.fxml";
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

    public void assignShiftsBTN(ActionEvent actionEvent){
        try {
            if(!DataBaseManager.getDBInstance().nextWeekExists()){
                DataBaseManager.getDBInstance().addWeekFinalChart();
            }
            String pathName = "ShiftsAssignment.fxml";
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
    public void salaryBTN(ActionEvent actionEvent){
        try {
            String pathName = "Salary.fxml";
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

    public void employeesDetailsBTN (){
        String res = Manager.getEmployeeDeets();
        JOptionPane.showMessageDialog(null, res);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] firstName = DataBaseManager.getDBInstance().getUser().split(" ", 2);
        this.nameT.setText("Hello, "+ firstName[0]);
    }

}
