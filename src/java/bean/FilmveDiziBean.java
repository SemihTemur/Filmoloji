package bean;

import dao.FilmveDiziDao;
import entity.Document;
import entity.FilmveDizi;
import java.sql.SQLException;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

@Named(value = "filmveDiziBean")
@SessionScoped
public class FilmveDiziBean implements Serializable {

    private FilmveDizi entity;
    private FilmveDiziDao dao;
    private List<FilmveDizi> list;
    private List<FilmveDizi> kategoriyitutanList;

    private FilmveDizi filmeGirenlist;

    private int tiklananId;

    private Part doc;

    private final String uploadTo = "/Users/berkaysari";

    public void upload() {
        if (doc != null) {
            try (InputStream input = doc.getInputStream()) {
                String fileName = doc.getSubmittedFileName();
                File file = new File(uploadTo, fileName);
                Files.copy(input, file.toPath());

                Document document = new Document();
                document.setFilePath(file.getAbsolutePath());
                document.setFileName(fileName);
                document.setFileType(doc.getContentType());

                this.getEntity().setDocument(document);

            } catch (IOException ex) {
                System.err.println("Dosya yüklenirken bir hata oluştu: " + ex.getMessage());
            }
        } else {
            System.err.println("Yüklenen dosya yok.");
        }

    }

    public FilmveDiziBean() {
    }

    public void create() {
        upload();
        System.out.print("asd" + entity.getDocument().getFileName());
        this.getDao().create(entity);
        this.entity = new FilmveDizi();
    }

    public void update() throws SQLException {
        upload();
        this.getDao().update(entity);
        entity = new FilmveDizi();
    }

    public void delete(FilmveDizi c) {
        this.getDao().delete(c);
    }

    public FilmveDizi getEntity() {
        if (this.entity == null) {
            entity = new FilmveDizi();

        }

        return entity;
    }

    public void setEntity(FilmveDizi entity) {

        this.entity = entity;
    }

    public FilmveDiziDao getDao() {
        if (this.dao == null) {
            this.dao = new FilmveDiziDao();
        }
        return dao;
    }

    public void setDao(FilmveDiziDao dao) {
        this.dao = dao;
    }
    

    public List<FilmveDizi> getList() {
        this.list = this.getDao().getFilmolojiList();
        return list;
    }

    public void setList(List<FilmveDizi> list) {
        this.list = list;
    }

    public FilmveDizi getFilmeGirenlist() {
        this.filmeGirenlist = this.getDao().getFilmeGirenKısım(tiklananId);
        return filmeGirenlist;
    }

    public void setFilmeGirenlist(FilmveDizi filmeGirenlist) {
        this.filmeGirenlist = filmeGirenlist;
    }

    public int getTiklananId() {
        return tiklananId;
    }

    public void listigetir() {
        this.kategoriyitutanList = this.getDao().getKategoriList(this.getEntity().getKullanicininSectigiKategori());
    }

    public List<FilmveDizi> getKategoriyitutanList() {
        return kategoriyitutanList;
    }

    public void setKategoriyitutanList(List<FilmveDizi> kategoriyitutanList) {
        this.kategoriyitutanList = this.getDao().getKategoriList("Hepsi");
    }

    public String navigateToGuncellePage(int id) {
        entity = new FilmveDizi();
        entity.setId(id);
        return "/AdminPaneli/FilmveDiziGuncelle?faces-redirect=true";
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public String getUploadTo() {
        return uploadTo;
    }

    public String setTiklananId(int tiklananId) {
        this.tiklananId = tiklananId;
        return "FilmeGirenKisim.xhtml?faces-redirect=true";
    }

}
