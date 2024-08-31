/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Document;
import entity.FilmveDizi;
import entity.Kitaplik;
import entity.Kullanıcı;
import entity.Platform;
import entity.İmdb;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author MERVAN
 */
public class KitaplikDAO extends DBConnection {

    public void create(int fd_id, int kullanici_id) throws SQLException {
        PreparedStatement pst = this.getConnect().prepareStatement("INSERT INTO kitaplık (kullanici_id, df_id) VALUES(?,?)");
        pst.setInt(1, kullanici_id);
        pst.setInt(2, fd_id);
        pst.executeUpdate();

    }

    public List<Kitaplik> getFilmeGirenKısım(int kullanici_id) {
        List<Kitaplik> seçilenFilm = new ArrayList<>();
        Kitaplik kitaplik = null;
        try {

            String query = "SELECT f.*, d.filepath, d.filename, d.filetype, u.kullanıcıadı, u.email "
                    + "FROM kitaplık k "
                    + "JOIN kullanıcılar u ON k.kullanici_id = u.kullanici_id "
                    + "JOIN filmvedizi f ON k.df_id = f.id "
                    + "LEFT JOIN documents d ON f.id = d.df_id "
                    + "WHERE k.kullanici_id = ?";

            PreparedStatement statement = this.getConnect().prepareStatement(query);
            statement.setInt(1, kullanici_id); // ID parametresini ayarlama

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                // Create Document object
                Document document = new Document(
                        resultSet.getString("filepath"),
                        resultSet.getString("filename"),
                        resultSet.getString("filetype")
                );

                // Create FilmveDizi object
                FilmveDizi filmveDizi = new FilmveDizi(
                        resultSet.getInt("id"),
                        resultSet.getString("tur"),
                        resultSet.getString("adi"),
                        resultSet.getString("konusu"),
                        resultSet.getString("kategorisi"),
                        resultSet.getString("yonetmen_adi"),
                        resultSet.getString("oyuncular"),
                        resultSet.getBoolean("vizyontrend"),
                        document
                );

                Kullanıcı kullanıcı = new Kullanıcı(
                        resultSet.getString("kullanıcıadı")
                );

                kitaplik = new Kitaplik(kullanıcı, filmveDizi);

                seçilenFilm.add(kitaplik);

            }

            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return seçilenFilm;
    }

    public void delete(int film_id,int kullanici_id) throws SQLException {
        String query = "DELETE FROM kitaplık WHERE df_id = ? AND kullanici_id = ?";

        PreparedStatement statement = this.getConnect().prepareStatement(query);
        statement.setInt(1, film_id);
        statement.setInt(2, kullanici_id);
        statement.executeUpdate();
    }

}
