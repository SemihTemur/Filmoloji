package bean;

import dao.TrenddekiDizilerDAO;
import entity.FilmveDizi;
import entity.TrenddekiDiziler;
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
@Named(value = "trenddekiDizilerBean")
@SessionScoped
public class TrenddekiDizilerBean implements Serializable {

    private TrenddekiDiziler entity;
    private TrenddekiDizilerDAO dao;
    private List<TrenddekiDiziler> list;
    
      private FilmveDizi filmeGirenlist;

    private int tiklananId;

    private final String uploadTo = "C:\\Temp\\upload";

    public TrenddekiDizilerBean() {
    }

    public void update() throws SQLException {
        //   this.getDao().update(entity);
        //   entity = new TrenddekiDiziler();
    }

    public void delete(TrenddekiDiziler c) {
        //   this.getDao().delete(c);
    }

    public TrenddekiDiziler getEntity() {
        if (this.entity == null) {
            entity = new TrenddekiDiziler();
        }
        return entity;
    }

    public void setEntity(TrenddekiDiziler entity) {
        this.entity = entity;
    }

    public TrenddekiDizilerDAO getDao() {
        if (this.dao == null) {
            this.dao = new TrenddekiDizilerDAO();
        }
        return dao;
    }

    public void setDao(TrenddekiDizilerDAO dao) {
        this.dao = dao;
    }

    public List<TrenddekiDiziler> getList() {
        this.list = this.getDao().getTrenddekiDiziler();
        return list;
    }

    public void setList(List<TrenddekiDiziler> list) {
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



    public String getUploadTo() {
        return uploadTo;
    }

      public String setTiklananId(int tiklananId) {
    this.tiklananId = tiklananId;
    return "TrendeGirenKisim.xhtml?faces-redirect=true";
}
    

}
