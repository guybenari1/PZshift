package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.DataBaseManager;

import java.net.URL;
import java.util.ResourceBundle;


public class MessagesController implements Initializable {
    @FXML
    private ListView employeesLV = new ListView();
    @FXML
    private ListView receiversLV = new ListView();
    @FXML
    private TextArea messageTA = new TextArea();

    public void doubleClickAdd(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) employeesLV.getSelectionModel().getSelectedItem();
            if (checkIfChosen(currentItemSelected)) {
                this.receiversLV.getItems().add(currentItemSelected);
                this.employeesLV.getItems().remove(currentItemSelected);
            }
        }
    }

    public void doubleClickRemove(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) receiversLV.getSelectionModel().getSelectedItem();
            this.receiversLV.getItems().remove(currentItemSelected);
            this.employeesLV.getItems().add(currentItemSelected);
        }
    }

    public boolean checkIfChosen(String name){
        int length = receiversLV.getItems().size();
        for (int i=0; i<length; i++){
            if (receiversLV.getItems().get(i).equals(name)){
                return false;
            }
        }
        return true;
    }

    @FXML
    public void sendBTN(ActionEvent event) {
        int length = receiversLV.getItems().size();
        //send messages
    }

    @FXML
    public void addAllBTN(ActionEvent event) {
        int length = this.employeesLV.getItems().size();
        for (int i=length-1; i>=0; i--){
            String currentItemSelected = (String) employeesLV.getItems().get(i);
            this.employeesLV.getItems().remove(currentItemSelected);
            this.receiversLV.getItems().add(currentItemSelected);
        }
    }

    @FXML
    public void clearAllBTN(ActionEvent event) {
        int length = this.receiversLV.getItems().size();
        for (int i=length-1; i>=0; i--){
            String currentItemSelected = (String) receiversLV.getItems().get(i);
            this.receiversLV.getItems().remove(currentItemSelected);
            this.employeesLV.getItems().add(currentItemSelected);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.employeesLV.getItems().addAll(DataBaseManager.getDBInstance().getWorkerNames());
    }
}
