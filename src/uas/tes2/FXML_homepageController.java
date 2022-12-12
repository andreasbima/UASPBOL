/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_homepageController implements Initializable {

    public static String benua;
    
    @FXML
    private Button btnhistory;
    @FXML
    private Button btnlogout;
    @FXML
    private Button btnamerikau;
    @FXML
    private Button btnamerikas;
    @FXML
    private Button btneropa;
    @FXML
    private Button btnafrika;
    @FXML
    private Button btnasia;
    @FXML
    private Label lbluser;
    @FXML
    private Button btnaustralia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbluser.setText(FXMLDocumentController.username);
    }

    @FXML
    private void historyklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_history.fxml"));
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
    private void logoutklik(ActionEvent event) {
        btnlogout.getScene().getWindow().hide();
    }

    @FXML
    private void amerikauklik(ActionEvent event) {
        benua = "Amerika Utara";
        gonegara();
    }

    @FXML
    private void amerikasklik(ActionEvent event) {
        benua = "Amerika Selatan";
        gonegara();
    }

    @FXML
    private void eropaklik(ActionEvent event) {
        benua = "Eropa";
        gonegara();
    }

    @FXML
    private void afrikaklik(ActionEvent event) {
        benua = "Afrika";
        gonegara();
    }

    @FXML
    private void asiaklik(ActionEvent event) {
        benua = "Asia";
        gonegara();
    }

    @FXML
    private void australiaklik(ActionEvent event) {
        benua = "Australia";
        gonegara();
    }
    
    private void gonegara(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_negara.fxml"));
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
