/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.faces.config.manager.Documents;
import entity.FilmveDizi;
import entity.Platform;
import entity.VizyondakiFilmler;
import entity.İmdb;
import entity.Document;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import java.sql.PreparedStatement;

public class VizyondakiFilmlerDAO extends DBConnection {

    public void create(FilmveDizi fd) {

        try {
            PreparedStatement pst = this.getConnect().prepareStatement("INSERT INTO vizyondakiler (df_id,olusturulma_tarihi) VALUES(?,?)");
            pst.setInt(1, fd.getId());
            pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<VizyondakiFilmler> getVizyondakiFilmler() {

        List<VizyondakiFilmler> categoryList = new ArrayList<>();

        try {
            String query = "SELECT f.*, d.filepath, d.filename, d.filetype, p.platform, i.imdb, v.olusturulma_tarihi "
             + "FROM vizyondakiler v "
             + "JOIN filmvedizi f ON v.df_id = f.id "
             + "LEFT JOIN documents d ON f.id = d.df_id "
             + "LEFT JOIN platform p ON f.id = p.df_id "
             + "LEFT JOIN imdb i ON f.id = i.df_id";

            
            PreparedStatement pst = this.getConnect().prepareStatement(query);

            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                // Create IMDb object
                İmdb imdb = new İmdb(
                        resultSet.getString("imdb")
                );

                // Create Platform object
                Platform platform = new Platform(
                        resultSet.getString("platform")
                );

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
                        resultSet.getString("kategorisi"), // assuming kategori is a single string
                        resultSet.getString("yonetmen_adi"),
                        resultSet.getString("oyuncular"),
                        resultSet.getBoolean("vizyontrend"),
                        imdb,
                        platform,
                        document
                );
                
                VizyondakiFilmler vf=new VizyondakiFilmler(filmveDizi, resultSet.getTimestamp("olusturulma_tarihi"));

                categoryList.add(vf);
            }

            resultSet.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categoryList;
    }

    public FilmveDizi getFilmeGirenKısım(int id) {
        List<FilmveDizi> seçilenFilm = new ArrayList<>();
        FilmveDizi filmveDizi = null;
        try {

            String query = "SELECT f.*, d.filepath, d.filename, d.filetype, p.platform, i.imdb "
                    + "FROM vizyondakiler v "
                    + "JOIN filmvedizi f ON v.df_id = f.id "
                    + "LEFT JOIN documents d ON f.id = d.df_id "
                    + "LEFT JOIN platform p ON f.id = p.df_id "
                    + "LEFT JOIN imdb i ON f.id = i.df_id "
                    + "WHERE f.id = ?";

            PreparedStatement statement = this.getConnect().prepareStatement(query);
            statement.setInt(1, id); // ID parametresini ayarlama

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Create IMDb object
                İmdb imdb = new İmdb(
                        resultSet.getString("imdb")
                        
                );

                // Create Platform object
                Platform platform = new Platform(
                        resultSet.getString("platform")
                );

                // Create Document object
                Document document = new Document(
                        resultSet.getString("filepath"),
                        resultSet.getString("filename"),
                        resultSet.getString("filetype")
                );

                // Create FilmveDizi object
                filmveDizi = new FilmveDizi(
                        resultSet.getInt("id"),
                        resultSet.getString("tur"),
                        resultSet.getString("adi"),
                        resultSet.getString("konusu"),
                        resultSet.getString("kategorisi"), // assuming kategori is a single string
                        resultSet.getString("yonetmen_adi"),
                        resultSet.getString("oyuncular"),
                        resultSet.getBoolean("vizyontrend"),
                        imdb,
                        platform,
                        document
                );

            }

            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return filmveDizi;
    }

//    public void update(FilmveDizi vf) throws SQLException {
//
//        PreparedStatement pst = this.getConnect().prepareStatement("update  VizyondakiFilmler set  (tur, adi, konusu, kategorisi, yonetmen_adi, oyuncular, vizyontrend,document_id) VALUES(?,?,?,?,?,?,?,?)");
//        pst.setString(1, vf.getTur());
//        pst.setString(2, vf.getAdi());
//        pst.setString(3, vf.getKonusu());
//        pst.setString(4, vf.getGönderilecekKategori());
//        pst.setString(5, vf.getYonetmen_adi());
//        pst.setString(6, vf.getOyuncular());
//        pst.setBoolean(7, vf.isVizyontrend());
//        pst.executeUpdate();
//
//    }
//    public void delete(FilmveDizi vf) {
//        try {
//
//            Statement st = (Statement) this.getConnect().createStatement();
//
//            String query0 = "UPDATE filmvedizi SET id = id - 1 WHERE id > " + vf.getId();
//            String query1 = "DELETE from filmvedizi where id=" + vf.getId();
//            st.executeUpdate(query1);
//            st.executeUpdate(query0);
//
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
}
