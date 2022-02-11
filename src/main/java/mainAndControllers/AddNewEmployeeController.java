package mainAndControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.DataBaseManager;
import model.Manager;
import model.Worker;
import javax.swing.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {
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
        String s;
        if (DataBaseManager.getDBInstance().noUsers() || managerCB.isSelected()){
            Manager temp = new Manager(nameTF.getText(), idTF.getText(), phoneTf.getText(), birthdateDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), emailTF.getText());
            s = Worker.workerProcess(temp);
        }
        else {
            Worker temp = new Worker(nameTF.getText(), idTF.getText(), phoneTf.getText(), birthdateDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), emailTF.getText());
            s = Worker.workerProcess(temp);
        }
        JOptionPane.showMessageDialog(null, s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (DataBaseManager.getDBInstance().noUsers()){
            this.managerCB.setSelected(true);
            this.managerCB.setDisable(true);
        }
    }
}
