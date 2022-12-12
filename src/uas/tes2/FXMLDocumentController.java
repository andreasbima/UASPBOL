/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package uas.tes2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {

    public static String username;
    public static DBUser dtuser = new DBUser();
    public static DBNegara dtnegara = new DBNegara();
    public static DBHistory dthistory = new DBHistory();

    @FXML
    private TextField txtuser;
    @FXML
    private TextField txtpassword;
    @FXML
    private Button btnmasuk;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btndaftar;
    @FXML
    private Button btnadmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void masukklik(ActionEvent event) {
        UserModel s = dtuser.check(txtuser.getText());
        if (txtuser.getText().equalsIgnoreCase("admin")) {
            if (txtpassword.getText().equalsIgnoreCase("admin")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_admin.fxml"));
                    Parent root = (Parent) loader.load();
                    Scene scene = new Scene(root);
                    Stage stg = new Stage();
                    stg.initModality(Modality.APPLICATION_MODAL);
                    stg.setResizable(false);
                    stg.setIconified(false);
                    stg.setScene(scene);
                    stg.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (txtuser.getText().equalsIgnoreCase(s.getUser())) {
            if (txtpassword.getText().equalsIgnoreCase(s.getPassword())) {
                username = txtuser.getText();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_homepage.fxml"));
                    Parent root = (Parent) loader.load();
                    Scene scene = new Scene(root);
                    Stage stg = new Stage();
                    stg.initModality(Modality.APPLICATION_MODAL);
                    stg.setResizable(false);
                    stg.setIconified(false);
                    stg.setScene(scene);
                    stg.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password yang dimasukkan salah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "User tidak terdaftar", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void daftarklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_daftaruser.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void adminklik(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_admin.fxml"));
//            Parent root = (Parent) loader.load();
//            Scene scene = new Scene(root);
//            Stage stg = new Stage();
//            stg.initModality(Modality.APPLICATION_MODAL);
//            stg.setResizable(false);
//            stg.setIconified(false);
//            stg.setScene(scene);
//            stg.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
