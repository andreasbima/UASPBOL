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
public class DBNegara {

    private NegaraModel dt = new NegaraModel();

    public NegaraModel getNegaraModel() {
        return (dt);
    }

    public void setNegaraModel(NegaraModel s) {
        dt = s;
    }

    public ObservableList<NegaraModel> Load() {
        try {
            ObservableList<NegaraModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select IDNegara, NamaNegara, IbuKota, Benua from negara");
            int i = 1;
            while (rs.next()) {
                NegaraModel d = new NegaraModel();
                d.setIDNegara(rs.getString("IDNegara"));
                d.setNamaNegara(rs.getString("NamaNegara"));
                d.setIbuKota(rs.getString("IbuKota"));
                d.setBenua(rs.getString("Benua"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from negara where IDNegara = " + nomor + "");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into negara (IDNegara, NamaNegara, IbuKota, Benua) values (?, ?, ?,?)");
            con.preparedStatement.setString(1, getNegaraModel().getIDNegara());
            con.preparedStatement.setString(2, getNegaraModel().getNamaNegara());
            con.preparedStatement.setString(3, getNegaraModel().getIbuKota());
            con.preparedStatement.setString(4, getNegaraModel().getBenua());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from negara where IDNegara = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update negara set NamaNegara = ?, IbuKota = ?, Benua = ? where IDNegara = ?;");
            con.preparedStatement.setString(1, getNegaraModel().getNamaNegara());
            con.preparedStatement.setString(2, getNegaraModel().getIbuKota());
            con.preparedStatement.setString(3, getNegaraModel().getBenua());
            con.preparedStatement.setString(4, getNegaraModel().getIDNegara());
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

    public ObservableList<NegaraModel> LookUp(String fld, String dt) {
        try {
            ObservableList<NegaraModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select IDNegara, NamaNegara, IbuKota, Benua from negara where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                NegaraModel d = new NegaraModel();
                d.setIDNegara(rs.getString("IDNegara"));
                d.setNamaNegara(rs.getString("NamaNegara"));
                d.setIbuKota(rs.getString("IbuKota"));
                d.setBenua(rs.getString("Benua"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
