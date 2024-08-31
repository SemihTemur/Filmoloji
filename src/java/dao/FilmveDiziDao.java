/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Document;
import entity.FilmveDizi;
import entity.Platform;
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
public class FilmveDiziDao extends DBConnection {

    private VizyondakiFilmlerDAO vfd;
    private TrenddekiDizilerDAO tfd;
    private PlatformDao ptd;
    private İmdbDao imdb;
    private DocumentDao dcd;

    public void create(FilmveDizi fd) {

        try {
            PreparedStatement pst = this.getConnect().prepareStatement("INSERT INTO filmvedizi (tur, adi, konusu, kategorisi, yonetmen_adi, oyuncular, vizyontrend) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, fd.getTur());
            pst.setString(2, fd.getAdi());
            pst.setString(3, fd.getKonusu());
            pst.setString(4, fd.getGönderilecekKategori());
            pst.setString(5, fd.getYonetmen_adi());
            pst.setString(6, fd.getOyuncular());
            pst.setBoolean(7, fd.isVizyontrend());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            int newFilmDiziId = 0;
            if (generatedKeys.next()) {
                newFilmDiziId = generatedKeys.getInt(1);
            }

            fd.setId(newFilmDiziId);

            this.getPtd().create(fd.getPlatform(), newFilmDiziId);
            this.getImdb().create(fd.getImdb(), newFilmDiziId);
            this.getDcd().create(fd.getDocument(), newFilmDiziId);

            if (fd.isVizyontrend() && fd.getTur().equals("Film")) {
                this.getVfd().create(fd);
            } else if (fd.isVizyontrend() && fd.getTur().equals("Dizi")) {
                this.getTfd().create(fd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<FilmveDizi> getFilmolojiList() {
        List<FilmveDizi> categoryList = new ArrayList<>();

        try {
            String query = "SELECT f.id, f.tur, f.adi, f.konusu, f.kategorisi, f.yonetmen_adi, f.oyuncular, f.vizyontrend, "
                    + "i.imdb_id, i.imdb, p.platform_id, p.platform, d.id AS document_id, d.filepath, d.filename, d.filetype "
                    + "FROM filmvedizi f "
                    + "LEFT JOIN imdb i ON f.id = i.df_id "
                    + "LEFT JOIN platform p ON f.id = p.df_id "
                    + "LEFT JOIN documents d ON f.id = d.df_id";
            PreparedStatement statement = this.getConnect().prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Create IMDb object
                İmdb imdb = new İmdb(
                        resultSet.getString("imdb"),
                        resultSet.getInt("id")
                );

                // Create Platform object
                Platform platform = new Platform(
                        resultSet.getInt("platform_id"),
                        resultSet.getString("platform")
                );

                // Create Document object
                Document document = new Document(
                        resultSet.getInt("document_id"),
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

                categoryList.add(filmveDizi);
            }

            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categoryList;
    }

    public FilmveDizi getFilmeGirenKısım(int id) {
        System.out.println(id);
        List<FilmveDizi> seçilenFilm = new ArrayList<>();
        FilmveDizi filmveDizi = null;
        try {

            String query = "SELECT f.id, f.tur, f.adi, f.konusu, f.kategorisi, f.yonetmen_adi, f.oyuncular, f.vizyontrend, "
                    + "i.imdb_id, i.imdb, p.platform_id, p.platform, d.id AS document_id, d.filepath, d.filename, d.filetype "
                    + "FROM filmvedizi f "
                    + "LEFT JOIN imdb i ON f.id = i.df_id "
                    + "LEFT JOIN platform p ON f.id = p.df_id "
                    + "LEFT JOIN documents d ON f.id = d.df_id "
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

    public List<FilmveDizi> getKategoriList(String seçilenKateogri) {
        List<FilmveDizi> categoryList = new ArrayList<>();
        String query;
        PreparedStatement statement;
        try {

            if (seçilenKateogri.equals("Hepsi")) {
                query = "SELECT f.id, f.tur, f.adi, f.konusu, f.kategorisi, f.yonetmen_adi, f.oyuncular, f.vizyontrend, "
                        + "i.imdb_id, i.imdb, p.platform_id, p.platform, d.id AS document_id, d.filepath, d.filename, d.filetype "
                        + "FROM filmvedizi f "
                        + "LEFT JOIN imdb i ON f.id = i.df_id "
                        + "LEFT JOIN platform p ON f.id = p.df_id "
                        + "LEFT JOIN documents d ON f.id = d.df_id ";
                statement = this.getConnect().prepareStatement(query);
               
            } else {
                query = "SELECT f.id, f.tur, f.adi, f.konusu, f.kategorisi, f.yonetmen_adi, f.oyuncular, f.vizyontrend, "
                        + "i.imdb_id, i.imdb, p.platform_id, p.platform, d.id AS document_id, d.filepath, d.filename, d.filetype "
                        + "FROM filmvedizi f "
                        + "LEFT JOIN imdb i ON f.id = i.df_id "
                        + "LEFT JOIN platform p ON f.id = p.df_id "
                        + "LEFT JOIN documents d ON f.id = d.df_id "
                        + "WHERE f.kategorisi LIKE ?";
                statement = this.getConnect().prepareStatement(query);
                statement.setString(1, "%" + seçilenKateogri + "%");
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Create IMDb object
                İmdb imdb = new İmdb(
                        resultSet.getString("imdb"),
                        resultSet.getInt("id")
                );

                // Create Platform object
                Platform platform = new Platform(
                        resultSet.getInt("platform_id"),
                        resultSet.getString("platform")
                );

                // Create Document object
                Document document = new Document(
                        resultSet.getInt("document_id"),
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

                categoryList.add(filmveDizi);
            }

            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return categoryList;
    }

    public void update(FilmveDizi fd) throws SQLException {

        String query1 = "UPDATE filmvedizi SET tur=?, adi=?, konusu=?, kategorisi=?, yonetmen_adi=?, oyuncular=?, vizyontrend=? WHERE id=?";
        PreparedStatement pst1 = this.getConnect().prepareStatement(query1);
        pst1.setString(1, fd.getTur());
        pst1.setString(2, fd.getAdi());
        pst1.setString(3, fd.getKonusu());
        pst1.setString(4, fd.getGönderilecekKategori());
        pst1.setString(5, fd.getYonetmen_adi());
        pst1.setString(6, fd.getOyuncular());
        pst1.setBoolean(7, fd.isVizyontrend());
        pst1.setInt(8, fd.getId());
        pst1.executeUpdate();

        // Update imdb table
        if (fd.getImdb() != null) {
            String query2 = "UPDATE imdb SET imdb=? WHERE filmvedizi_id=?";
            PreparedStatement pst2 = this.getConnect().prepareStatement(query2);
            pst2.setString(1, fd.getImdb().getImdb());
            pst2.executeUpdate();
        }

        // Update platform table
        if (fd.getPlatform() != null) {
            String query3 = "UPDATE platform SET platform=? WHERE filmvedizi_id=?";
            PreparedStatement pst3 = this.getConnect().prepareStatement(query3);
            pst3.setString(1, fd.getPlatform().getPlatform_adi());
            pst3.executeUpdate();
        }

        // Update documents table
        if (fd.getDocument() != null) {
            String query4 = "UPDATE documents SET filepath=?, filename=?, filetype=? WHERE filmvedizi_id=?";
            PreparedStatement pst4 = this.getConnect().prepareStatement(query4);
            pst4.setString(1, fd.getDocument().getFilePath());
            pst4.setString(2, fd.getDocument().getFileName());
            pst4.setString(3, fd.getDocument().getFileType());
            pst4.executeUpdate();
        }

    }

    public void delete(FilmveDizi fd) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query1 = "DELETE from filmvedizi where id=" + fd.getId();
            st.executeUpdate(query1);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public VizyondakiFilmlerDAO getVfd() {
        if (vfd == null) {
            vfd = new VizyondakiFilmlerDAO();
        }
        return vfd;
    }

    public void setVfd(VizyondakiFilmlerDAO vfd) {
        this.vfd = vfd;
    }

    public TrenddekiDizilerDAO getTfd() {
        if (tfd == null) {
            tfd = new TrenddekiDizilerDAO();
        }
        return tfd;
    }

    public void setTfd(TrenddekiDizilerDAO tfd) {
        this.tfd = tfd;
    }

    public PlatformDao getPtd() {
        if (this.ptd == null) {
            ptd = new PlatformDao();
        }
        return ptd;
    }

    public void setPtd(PlatformDao ptd) {
        this.ptd = ptd;
    }

    public İmdbDao getImdb() {
        if (this.imdb == null) {
            imdb = new İmdbDao();
        }
        return imdb;
    }

    public void setImdb(İmdbDao imdb) {
        this.imdb = imdb;
    }

    public DocumentDao getDcd() {
        if (this.dcd == null) {
            this.dcd = new DocumentDao();
        }
        return dcd;
    }

    public void setDcd(DocumentDao dcd) {
        this.dcd = dcd;
    }

}
