/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas.tes2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_adminController implements Initializable {

    @FXML
    private TableView<NegaraModel> tbvnegara;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnedit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkeluar;
    @FXML
    private TextField txtcari;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    private void showdata() {
        ObservableList<NegaraModel> data = FXMLDocumentController.dtnegara.Load();
        if (data != null) {
            show(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvnegara.getScene().getWindow().hide();
        }
    }
   
    private void show(ObservableList<NegaraModel> data) {
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
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_inputnegara.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
    }

    @FXML
    private void editklik(ActionEvent event) {
        NegaraModel s = new NegaraModel();
        s = tbvnegara.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_inputnegara.fxml"));
            Parent root = (Parent) loader.load();
            FXML_inputnegaraController isidt = (FXML_inputnegaraController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        NegaraModel s = new NegaraModel();
        s = tbvnegara.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtnegara.delete(s.getIDNegara())) {
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
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void tuliscari(KeyEvent event) {
        NegaraModel s = new NegaraModel();
        String key = txtcari.getText();
        if (key != "") {
            ObservableList<NegaraModel> data = FXMLDocumentController.dtnegara.LookUp("Benua", key);
            if (data != null) {
                show(data);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
                a.showAndWait();
                tbvnegara.getScene().getWindow().hide();;
            }
        } else {
            showdata();
        }
    }
}
