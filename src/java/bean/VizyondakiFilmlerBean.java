/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.VizyondakiFilmlerDAO;
import entity.FilmveDizi;
import entity.VizyondakiFilmler;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author semih
 */
@Named(value = "vizyondakiFilmlerBean")
@SessionScoped
public class VizyondakiFilmlerBean implements Serializable {

    private VizyondakiFilmler entity;
    private VizyondakiFilmlerDAO dao;
    private List<VizyondakiFilmler> list;
    private FilmveDizi filmeGirenlist;

    private int tiklananId;

    private final String uploadTo = "C:\\Temp\\upload";

    public VizyondakiFilmlerBean() {
    }

    public void update() throws SQLException {
        //   this.getDao().update(entity);
        //   entity = new VizyondakiFilmler();
    }

    public void delete(VizyondakiFilmler c) {
        //   this.getDao().delete(c);
    }

    public VizyondakiFilmler getEntity() {
        if (this.entity == null) {
            entity = new VizyondakiFilmler();
        }
        return entity;
    }

    public void setEntity(VizyondakiFilmler entity) {
        this.entity = entity;
    }

    public VizyondakiFilmlerDAO getDao() {
        if (this.dao == null) {
            this.dao = new VizyondakiFilmlerDAO();
        }
        return dao;
    }

    public void setDao(VizyondakiFilmlerDAO dao) {
        this.dao = dao;
    }

    public List<VizyondakiFilmler> getList() {
        this.list = this.getDao().getVizyondakiFilmler();
        return list;
    }

    public void setList(List<VizyondakiFilmler> list) {
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
    
    

    public String navigateToGuncellePage(int id) {
        entity = new VizyondakiFilmler();
        // entity.setId(id);
        return "/AdminPaneli/VizyondakiFilmlerGuncelle?faces-redirect=true";
    }

    public String getUploadTo() {
        return uploadTo;
    }

      public String setTiklananId(int tiklananId) {
    this.tiklananId = tiklananId;
    return "VizyonaGirenKisim.xhtml?faces-redirect=true";
}
}
