package main;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import javax.swing.*;

public class SubmitShiftsController {
    @FXML
    private CheckBox sunMCB = new CheckBox();
    @FXML
    private CheckBox sunECB = new CheckBox();
    @FXML
    private CheckBox monMCB = new CheckBox();
    @FXML
    private CheckBox monECB = new CheckBox();
    @FXML
    private CheckBox tueMCB = new CheckBox();
    @FXML
    private CheckBox tueECB = new CheckBox();
    @FXML
    private CheckBox wedMCB = new CheckBox();
    @FXML
    private CheckBox wedECB = new CheckBox();
    @FXML
    private CheckBox thuMCB = new CheckBox();
    @FXML
    private CheckBox thuECB = new CheckBox();
    @FXML
    private CheckBox friMCB = new CheckBox();
    @FXML
    private CheckBox friECB = new CheckBox();
    @FXML
    private CheckBox satMCB = new CheckBox();
    @FXML
    private CheckBox satECB = new CheckBox();


    public void submitBTN(){
        JOptionPane.showMessageDialog(null, "shifts submitted");
    }
}
