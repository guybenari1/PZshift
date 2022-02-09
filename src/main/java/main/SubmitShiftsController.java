package main;

import Shift_Commander.Shift;
import Shift_Commander.ShiftHandler;
import Shift_Commander.addShift;
import com.mongodb.MongoException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import model.DataBaseManager;

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
        Boolean[] choiceArr= new Boolean[14];
        choiceArr[0] = sunMCB.isSelected();
        choiceArr[1] = sunECB.isSelected();
        choiceArr[2] = monMCB.isSelected();
        choiceArr[3] =monECB.isSelected();
        choiceArr[4] =tueMCB.isSelected();
        choiceArr[5]=tueECB.isSelected();
        choiceArr[6]=wedMCB.isSelected();
        choiceArr[7]=wedECB.isSelected();
        choiceArr[8]=thuMCB.isSelected();
        choiceArr[9]=thuECB.isSelected();
        choiceArr[10]=friMCB.isSelected();
        choiceArr[11]=friECB.isSelected();
        choiceArr[12]=satMCB.isSelected();
        choiceArr[13]=satECB.isSelected();
        ShiftHandler handler = new ShiftHandler();
        for (int i=0; i<14 ; i++){
            if(choiceArr[i]){
                Shift temp = new Shift(i,DataBaseManager.getDBInstance().getUser());
                addShift tempo = new addShift(temp);
                handler.takeRequest(tempo);
            }
        }
        try{
            handler.placeRequests();
        }catch (MongoException err){
            System.err.println("Program ran into error: "+err);
        }

        JOptionPane.showMessageDialog(null, "shifts submitted");
    }
}
