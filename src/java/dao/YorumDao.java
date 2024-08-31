package dao;

import entity.Yorum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class YorumDao extends DBConnection {

    public void create(int df_id, String yorum, int kullanici_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.getConnect();
            String sql = "INSERT INTO yorum (df_id, yorum, kullanici_id) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, df_id);
            statement.setString(2, yorum);
            statement.setInt(3, kullanici_id);
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

    public void delete(int df_id, int kullanici_id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.getConnect();
            String sql = "DELETE FROM yorum WHERE df_id = ? AND kullanici_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, df_id);
            statement.setInt(2, kullanici_id);
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

    public List<Yorum> getFilmeGirenKısım(int kullanici_id) {
        List<Yorum> list = new ArrayList<>();
        try {
            Connection connection = this.getConnect();
            String sql = "SELECT * FROM yorum WHERE kullanici_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, kullanici_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Yorum yorum = new Yorum();
                yorum.setDf_id(resultSet.getInt("df_id"));
                yorum.setYorum(resultSet.getString("yorum"));
                yorum.setKullanici_id(resultSet.getInt("kullanici_id"));
                list.add(yorum);
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
