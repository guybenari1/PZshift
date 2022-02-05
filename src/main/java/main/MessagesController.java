package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
            }
        }
    }

    public void doubleClickRemove(MouseEvent click) {
        if (click.getClickCount() == 2) {
            String currentItemSelected;
            currentItemSelected = (String) receiversLV.getSelectionModel().getSelectedItem();
            this.receiversLV.getItems().remove(currentItemSelected);
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
    void sendBTN(ActionEvent event) {
        int length = receiversLV.getItems().size();
        System.out.printf("you choose : %d people to get this message:\n", length);
        System.out.println(messageTA.getText());
        for (int i=1; i<=length; i++){
            System.out.printf("#%d receiver: %s\n",i,receiversLV.getItems().get(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.employeesLV.getItems().addAll("guy ben ari", "dor daniel", "gali galgali"
                , "omer tsur", "david davidan", "inbal boli", "raz magori", "elad eldadi");
    }
}
