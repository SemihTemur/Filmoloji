/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Document;
import entity.FilmveDizi;
import entity.Platform;
import entity.TrenddekiDiziler;
import entity.VizyondakiFilmler;
import entity.İmdb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import java.sql.PreparedStatement;

/**
 *
 * @author semih
 */
public class TrenddekiDizilerDAO extends DBConnection {

    public void create(FilmveDizi fd) {

        try {
             PreparedStatement pst = this.getConnect().prepareStatement("INSERT INTO trenddekiler (df_id,olusturulma_tarihi) VALUES(?,?)");
            pst.setInt(1, fd.getId());
            pst.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<TrenddekiDiziler> getTrenddekiDiziler() {

        List<TrenddekiDiziler> categoryList = new ArrayList<>();

        try {

            String query = "SELECT f.*, d.filepath, d.filename, d.filetype, p.platform, i.imdb, t.olusturulma_tarihi "
                    + "FROM trenddekiler t "
                    + "JOIN filmvedizi f ON t.df_id = f.id "
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
                
                 TrenddekiDiziler tf=new TrenddekiDiziler(filmveDizi, resultSet.getTimestamp("olusturulma_tarihi"));

                categoryList.add(tf);
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
                    + "FROM trenddekiler t "
                    + "JOIN filmvedizi f ON t.df_id = f.id "
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

}
