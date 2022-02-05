package main;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javax.swing.*;

public class SalaryController {
    @FXML
    private ChoiceBox employeeCB = new ChoiceBox();
    @FXML
    private TextField newSalaryTF = new TextField();

    public void submitBTN(){
        JOptionPane.showMessageDialog(null, "new salary submitted");
    }
}
