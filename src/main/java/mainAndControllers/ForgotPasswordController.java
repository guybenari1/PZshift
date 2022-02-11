package mainAndControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.LoginMaster;
import javax.swing.*;

public class ForgotPasswordController {
    @FXML
    private TextField emailTF = new TextField();

    @FXML
    void sendCodeBTN(ActionEvent event) {
        String res = LoginMaster.getInstance().forgotPassword(emailTF.getText());
        JOptionPane.showMessageDialog(null,res);
    }

}
