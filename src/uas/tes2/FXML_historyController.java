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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_historyController implements Initializable {

    @FXML
    private TableView<HistoryModel> tbvhistory;
    @FXML
    private Button btndelete;
    @FXML
    private TextField txtcari;
    @FXML
    private Button btnexit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    private void showdata() {
        ObservableList<HistoryModel> data = FXMLDocumentController.dthistory.Load(FXMLDocumentController.username);
        if (data != null) {
            show(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvhistory.getScene().getWindow().hide();
        }
    }
    public void show(ObservableList<HistoryModel> data){
        tbvhistory.getColumns().clear();
            tbvhistory.getItems().clear();
            TableColumn col = new TableColumn("IDHistory");
            col.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("IDHistory"));
            tbvhistory.getColumns().addAll(col);
            col = new TableColumn("User");
            col.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("User"));
            tbvhistory.getColumns().addAll(col);
            col = new TableColumn("Benua");
            col.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("Benua"));
            tbvhistory.getColumns().addAll(col);
            col = new TableColumn("IDNegara");
            col.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("IDNegara"));
            tbvhistory.getColumns().addAll(col);
            tbvhistory.setItems(data);
    }

    @FXML
    private void deleteklik(ActionEvent event) {
        HistoryModel s = new HistoryModel();
        s = tbvhistory.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dthistory.delete(s.getIDHistory())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
        }
    }

    @FXML
    private void exitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void tuliscari(KeyEvent event) {
        HistoryModel s = new HistoryModel();
        String key = txtcari.getText();
        if (key != "") {
            ObservableList<HistoryModel> data = FXMLDocumentController.dthistory.LookUp(FXMLDocumentController.username, key);
            if (data != null) {
                show(data);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
                a.showAndWait();
                tbvhistory.getScene().getWindow().hide();;
            }
        } else {
            showdata();
        }
    }
}
