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
public class FXML_inputnegaraController implements Initializable {

    boolean editdata = false;

    @FXML
    private TextField txtidnegara;
    @FXML
    private TextField txtnamanegara;
    @FXML
    private TextField txtbenua;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtibukota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void execute(NegaraModel d) {
        if (!d.getIDNegara().isEmpty()) {
            editdata = true;
            txtidnegara.setText(d.getIDNegara());
            txtnamanegara.setText(d.getNamaNegara());
            txtibukota.setText(d.getIbuKota());
            txtbenua.setText(d.getBenua());
            txtidnegara.setEditable(false);
            txtnamanegara.requestFocus();
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        NegaraModel s = new NegaraModel();
        s.setIDNegara(txtidnegara.getText());
        s.setNamaNegara(txtnamanegara.getText());
        s.setIbuKota(txtibukota.getText());
        s.setBenua(txtbenua.getText());
        FXMLDocumentController.dtnegara.setNegaraModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtnegara.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtidnegara.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtnegara.validasi(s.getIDNegara()) <= 0) {
            if (FXMLDocumentController.dtnegara.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtidnegara.requestFocus();
        }
    } @FXML
    private void batalklik(ActionEvent event) {
        txtidnegara.setText("");
        txtnamanegara.setText("");
        txtibukota.setText("");
        txtbenua.setText("");
        txtidnegara.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

}
