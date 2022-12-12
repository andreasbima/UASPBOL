/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas.tes2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_daftaruserController implements Initializable {

    boolean editdata = false;

    @FXML
    private TextField txtuser;
    @FXML
    private TextField txtpassword;
    @FXML
    private Button btndaftar;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void daftarklik(ActionEvent event) {
        UserModel s = new UserModel();
        s.setUser(txtuser.getText());
        s.setPassword(txtpassword.getText());
        FXMLDocumentController.dtuser.setUserModel(s);
        if (FXMLDocumentController.dtuser.validasi(s.getUser()) <= 0) {
            if (FXMLDocumentController.dtuser.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "User berhasil terdaftar", ButtonType.OK);
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "User gagal terdaftar", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "User sudah terdaftar", ButtonType.OK);
            a.showAndWait();
            txtuser.requestFocus();
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

}
