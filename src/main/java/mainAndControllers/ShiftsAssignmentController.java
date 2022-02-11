package mainAndControllers;

import com.mongodb.MongoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.DataBaseManager;
import model.Holidays;
import model.Manager;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class ShiftsAssignmentController implements Initializable {
    @FXML
    private AnchorPane assignAP = new AnchorPane();
    @FXML
    private AnchorPane showAP = new AnchorPane();
    @FXML
    private ListView <String> sunMLV = new ListView<String>();
    @FXML
    private ListView <String> sunELV = new ListView<String>();
    @FXML
    private ListView <String> monMLV = new ListView<String>();
    @FXML
    private ListView <String> monELV = new ListView<String>();
    @FXML
    private ListView <String> tueMLV = new ListView<String>();
    @FXML
    private ListView <String> tueELV = new ListView<String>();
    @FXML
    private ListView <String> wedMLV = new ListView<String>();
    @FXML
    private ListView <String> wedELV = new ListView<String>();
    @FXML
    private ListView <String> thuMLV = new ListView<String>();
    @FXML
    private ListView <String> thuELV = new ListView<String>();
    @FXML
    private ListView <String> friMLV = new ListView<String>();
    @FXML
    private ListView <String> friELV = new ListView<String>();
    @FXML
    private ListView <String> satMLV = new ListView<String>();
    @FXML
    private ListView <String> satELV = new ListView<String>();
    @FXML
    private ListView <String> sunMLVSub = new ListView<String>();
    @FXML
    private ListView <String> sunELVSub = new ListView<String>();
    @FXML
    private ListView <String> monMLVSub = new ListView<String>();
    @FXML
    private ListView <String> monELVSub = new ListView<String>();
    @FXML
    private ListView <String> tueMLVSub = new ListView<String>();
    @FXML
    private ListView <String> tueELVSub = new ListView<String>();
    @FXML
    private ListView <String> wedMLVSub = new ListView<String>();
    @FXML
    private ListView <String> wedELVSub = new ListView<String>();
    @FXML
    private ListView <String> thuMLVSub = new ListView<String>();
    @FXML
    private ListView <String> thuELVSub = new ListView<String>();
    @FXML
    private ListView <String> friMLVSub = new ListView<String>();
    @FXML
    private ListView <String> friELVSub = new ListView<String>();
    @FXML
    private ListView <String> satMLVSub = new ListView<String>();
    @FXML
    private ListView <String> satELVSub = new ListView<String>();

    public void doubleClickAdd1M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) sunMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, sunMLVSub)) {
                this.sunMLVSub.getItems().add(currentItemSelected);
                this.sunMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove1M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) sunMLVSub.getSelectionModel().getSelectedItem();
            this.sunMLVSub.getItems().remove(currentItemSelected);
            this.sunMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd2M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) monMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, monMLVSub)) {
                this.monMLVSub.getItems().add(currentItemSelected);
                this.monMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove2M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) monMLVSub.getSelectionModel().getSelectedItem();
            this.monMLVSub.getItems().remove(currentItemSelected);
            this.monMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd3M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) tueMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, tueMLVSub)) {
                this.tueMLVSub.getItems().add(currentItemSelected);
                this.tueMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove3M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) tueMLVSub.getSelectionModel().getSelectedItem();
            this.tueMLVSub.getItems().remove(currentItemSelected);
            this.tueMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd4M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) wedMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, wedMLVSub)) {
                this.wedMLVSub.getItems().add(currentItemSelected);
                this.wedMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove4M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) wedMLVSub.getSelectionModel().getSelectedItem();
            this.wedMLVSub.getItems().remove(currentItemSelected);
            this.wedMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd5M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) thuMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, thuMLVSub)) {
                this.thuMLVSub.getItems().add(currentItemSelected);
                this.thuMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove5M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) thuMLVSub.getSelectionModel().getSelectedItem();
            this.thuMLVSub.getItems().remove(currentItemSelected);
            this.thuMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd6M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) friMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, friMLVSub)) {
                this.friMLVSub.getItems().add(currentItemSelected);
                this.friMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove6M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) friMLVSub.getSelectionModel().getSelectedItem();
            this.friMLVSub.getItems().remove(currentItemSelected);
            this.friMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd7M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) satMLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, satMLVSub)) {
                this.satMLVSub.getItems().add(currentItemSelected);
                this.satMLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove7M(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) satMLVSub.getSelectionModel().getSelectedItem();
            this.satMLVSub.getItems().remove(currentItemSelected);
            this.satMLV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd1E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) sunELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, sunELVSub)) {
                this.sunELVSub.getItems().add(currentItemSelected);
                this.sunELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove1E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) sunELVSub.getSelectionModel().getSelectedItem();
            this.sunELVSub.getItems().remove(currentItemSelected);
            this.sunELV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd2E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) monELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, monELVSub)) {
                this.monELVSub.getItems().add(currentItemSelected);
                this.monELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove2E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) monELVSub.getSelectionModel().getSelectedItem();
            this.monELVSub.getItems().remove(currentItemSelected);
            this.monELV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd3E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) tueELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, tueELVSub)) {
                this.tueELVSub.getItems().add(currentItemSelected);
                this.tueELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove3E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) tueELVSub.getSelectionModel().getSelectedItem();
            this.tueELVSub.getItems().remove(currentItemSelected);
            this.tueELV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd4E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) wedELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, wedELVSub)) {
                this.wedELVSub.getItems().add(currentItemSelected);
                this.wedELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove4E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) wedELVSub.getSelectionModel().getSelectedItem();
            this.wedELVSub.getItems().remove(currentItemSelected);
            this.wedELV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd5E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) thuELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, thuELVSub)) {
                this.thuELVSub.getItems().add(currentItemSelected);
                this.thuELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove5E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) thuELVSub.getSelectionModel().getSelectedItem();
            this.thuELVSub.getItems().remove(currentItemSelected);
            this.thuELV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd6E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) friELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, friELVSub)) {
                this.friELVSub.getItems().add(currentItemSelected);
                this.friELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove6E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) friELVSub.getSelectionModel().getSelectedItem();
            this.friELVSub.getItems().remove(currentItemSelected);
            this.friELV.getItems().add(currentItemSelected);
        }
    }

    public void doubleClickAdd7E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) satELV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected, satELVSub)) {
                this.satELVSub.getItems().add(currentItemSelected);
                this.satELV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove7E(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) satELVSub.getSelectionModel().getSelectedItem();
            this.satELVSub.getItems().remove(currentItemSelected);
            this.satELV.getItems().add(currentItemSelected);
        }
    }

    public boolean checkIfChosen(String name, ListView <String> listView){
        int length = listView.getItems().size();
        for (int i=0; i<length; i++){
            if (listView.getItems().get(i).equals(name)){
                return false;
            }
        }
        return true;
    }

    public void showBTN(){
        if (assignAP.isVisible()){
            assignAP.setVisible(false);
            showAP.setVisible(true);
        }
        else {
            assignAP.setVisible(true);
            showAP.setVisible(false);
        }
    }

    public void publishBTN(){
        try{
            Manager.shiftAdderMaster(0,0,this.sunMLVSub);
            Manager.shiftAdderMaster(0,0,this.sunELVSub);
            Manager.shiftAdderMaster(1,0,this.monMLVSub);
            Manager.shiftAdderMaster(1,1,this.monELVSub);
            Manager.shiftAdderMaster(2,0,this.tueMLVSub);
            Manager.shiftAdderMaster(2,1,this.tueELVSub);
            Manager.shiftAdderMaster(3,0,this.wedMLVSub);
            Manager.shiftAdderMaster(3,1,this.wedELVSub);
            Manager.shiftAdderMaster(4,0,this.thuMLVSub);
            Manager.shiftAdderMaster(4,1,this.thuELVSub);
            Manager.shiftAdderMaster(5,0,this.friMLVSub);
            Manager.shiftAdderMaster(5,1,this.friELVSub);
            Manager.shiftAdderMaster(6,0,this.satMLVSub);
            Manager.shiftAdderMaster(6,1,this.satELVSub);
        } catch (MongoException err){
            System.err.println("System ran into an error "+ err);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sunMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(0,0));
        this.sunELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(0,1));
        this.monMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(1,0));
        this.monELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(1,1));
        this.tueMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(2,0));
        this.tueELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(2,1));
        this.wedMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(3,0));
        this.wedELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(3,1));
        this.thuMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(4,0));
        this.thuELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(4,1));
        this.friMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(5,0));
        this.friELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(5,1));
        this.satMLV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(6,0));
        this.satELV.getItems().addAll(DataBaseManager.getDBInstance().shiftSignUp(6,1));
        if (Holidays.holidaysInWeek()!=null){
            JOptionPane.showMessageDialog(null, "in the follow week there will be these holidays: " + Holidays.holidaysInWeek());
        }
    }
}
