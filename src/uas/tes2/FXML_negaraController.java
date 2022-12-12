/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas.tes2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_negaraController implements Initializable {

    private String negara;

    @FXML
    private TableView<NegaraModel> tbvnegara;
    @FXML
    private Button btnpernah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    private void showdata() {
        ObservableList<NegaraModel> data = FXMLDocumentController.dtnegara.LookUp("Benua", FXML_homepageController.benua);
        if (data != null) {
            tbvnegara.getColumns().clear();
            tbvnegara.getItems().clear();
            TableColumn col = new TableColumn("Benua");
            col.setCellValueFactory(new PropertyValueFactory<NegaraModel, String>("Benua"));
            tbvnegara.getColumns().addAll(col);
            col = new TableColumn("IDNegara");
            col.setCellValueFactory(new PropertyValueFactory<NegaraModel, String>("IDNegara"));
            tbvnegara.getColumns().addAll(col);
            col = new TableColumn("NamaNegara");
            col.setCellValueFactory(new PropertyValueFactory<NegaraModel, String>("NamaNegara"));
            tbvnegara.getColumns().addAll(col);
            col = new TableColumn("IbuKota");
            col.setCellValueFactory(new PropertyValueFactory<NegaraModel, String>("IbuKota"));
            tbvnegara.getColumns().addAll(col);
            tbvnegara.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvnegara.getScene().getWindow().hide();
        }
    }

    @FXML
    private void pernahklik(ActionEvent event) {
        HistoryModel s = new HistoryModel();
        s.setIDHistory(FXMLDocumentController.username + negara);
        s.setUser(FXMLDocumentController.username);
        s.setIDNegara(negara);
        FXMLDocumentController.dthistory.setHistoryModel(s);
        
        if (FXMLDocumentController.dthistory.validasi(s.getIDHistory()) <= 0) {
            if (FXMLDocumentController.dthistory.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void negarapilih(MouseEvent event
    ) {
        negara = tbvnegara.getSelectionModel().getSelectedItem().getIDNegara();
    }

}
