package dao;

import entity.Mesajlar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class MesajlarDao extends DBConnection {

    public void create(int kullanici_id, String mesaj) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.getConnect();
            String sql = "INSERT INTO mesajlar (kullanici_id, mesaj) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, kullanici_id);
            statement.setString(2, mesaj);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void delete(int mesaj_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.getConnect();
            String sql = "DELETE FROM mesajlar WHERE mesaj_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, mesaj_id);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Mesajlar> getMesajlar(int kullanici_id) {
        List<Mesajlar> list = new ArrayList<>();
        try {
            Connection connection = this.getConnect();
            String sql = "SELECT * FROM mesajlar WHERE kullanici_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, kullanici_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mesajlar mesaj = new Mesajlar();
                mesaj.setMesaj_id(resultSet.getInt("mesaj_id"));
                mesaj.setMesaj(resultSet.getString("mesaj"));
                mesaj.setKullanici_id(resultSet.getInt("kullanici_id"));
                list.add(mesaj);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
