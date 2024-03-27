package mainAndControllers;

import com.mongodb.MongoException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.DataBaseManager;
import model.Manager;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {
    @FXML
    private ChoiceBox employeeCB = new ChoiceBox();
    @FXML
    private TextField newSalaryTF = new TextField();

    public void submitBTN(){
        try {
           Manager.updateSal((String)employeeCB.getSelectionModel().getSelectedItem(), newSalaryTF.getText());
           JOptionPane.showMessageDialog(null, "new salary submitted");
        }
        catch (MongoException err){
            System.err.println("System ran into an error: " +err);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.employeeCB.setItems(FXCollections.observableArrayList(DataBaseManager.getDBInstance().getWorkerNames()));
    }
}
