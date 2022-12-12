/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.tes2;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class DBHistory {
    private HistoryModel dt = new HistoryModel();

    public HistoryModel getHistoryModel() {
        return (dt);
    }

    public void setHistoryModel(HistoryModel s) {
        dt = s;
    }

    public ObservableList<HistoryModel> Load(String user) {
        try {
            ObservableList<HistoryModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select IDHistory, User, n.Benua, n.IDNegara from history h join "
                    + "negara n on (n.IDNegara = h.IDNegara) where User = '" + user + "'");
            int i = 1;
            while (rs.next()) {
                HistoryModel d = new HistoryModel();
                d.setIDHistory(rs.getString("IDHistory"));
                d.setUser(rs.getString("User"));
                d.setBenua(rs.getString("Benua"));
                d.setIDNegara(rs.getString("IDNegara"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from history where IDHistory = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into history (IDHistory, User, IDNegara) "
                    + "values (?, ?, ?)");
            con.preparedStatement.setString(1, getHistoryModel().getIDHistory());
            con.preparedStatement.setString(2, getHistoryModel().getUser());
            con.preparedStatement.setString(3, getHistoryModel().getIDNegara());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from history where IDHistory = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update history set User = ?, IDNegara = ? where IDHistory = ?;");
            con.preparedStatement.setString(1, getHistoryModel().getUser());
            con.preparedStatement.setString(2, getHistoryModel().getIDNegara());
            con.preparedStatement.setString(3, getHistoryModel().getIDHistory());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public ObservableList<HistoryModel> LookUp(String user, String dt) {
        try {
            ObservableList<HistoryModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select IDHistory, User, n.Benua, n.IDNegara from history h join "
                    + "negara n on (n.IDNegara = h.IDNegara) where User = '" + user + "' and Benua like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                HistoryModel d = new HistoryModel();
                d.setIDHistory(rs.getString("IDHistory"));
                d.setUser(rs.getString("User"));
                d.setBenua(rs.getString("Benua"));
                d.setIDNegara(rs.getString("IDNegara"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
