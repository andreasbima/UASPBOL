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
public class DBUser {

    private UserModel dt = new UserModel();

    public UserModel getUserModel() {
        return (dt);
    }

    public void setUserModel(UserModel s) {
        dt = s;
    }

    public ObservableList<UserModel> Load() {
        try {
            ObservableList<UserModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select User, Password from user");
            int i = 1;
            while (rs.next()) {
                UserModel d = new UserModel();
                d.setUser(rs.getString("User"));
                d.setPassword(rs.getString("Password"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from user where User = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into user (User, Password) values (?,?)");
            con.preparedStatement.setString(1, getUserModel().getUser());
            con.preparedStatement.setString(2, getUserModel().getPassword());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from user where User = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update user set Password = ? where User = ?;");
            con.preparedStatement.setString(1, getUserModel().getPassword());
            con.preparedStatement.setString(2, getUserModel().getUser());
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

    public ObservableList<UserModel> LookUp(String fld, String dt) {
        try {
            ObservableList<UserModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select User, Password from user where " + fld
                    + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                UserModel d = new UserModel();
                d.setUser(rs.getString("User"));
                d.setPassword(rs.getString("Password"));
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

    public UserModel check(String a) {
        try {
            UserModel user = new UserModel();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select * from user where User ='" + a +"'");
            int i = 1;
            while (rs.next()) {
                user.setUser(rs.getString("User"));
                user.setPassword(rs.getString("Password"));
            }
            con.tutupKoneksi();
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
