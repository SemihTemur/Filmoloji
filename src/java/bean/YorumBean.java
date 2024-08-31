package bean;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import dao.YorumDao;
import entity.Yorum;

@Named(value = "yorumBean")
@SessionScoped
public class YorumBean implements Serializable {

    private Yorum entity;
    private YorumDao dao;
    private List<Yorum> list;

    private int currentKullaniciId; // Oturum açmış kullanıcının ID'si
    private int currentDfId; // Şu anki film/dizi ID'si

    public YorumBean() {
        entity = new Yorum();
    }

    @PostConstruct
    public void init() {
        // Geçerli kullanıcı ve film/dizi ID'lerini burada ayarlayın
        this.currentKullaniciId = getLoggedInUserId();
        this.currentDfId = getCurrentFilmId();
        loadYorumlar(); // Yorumları yükle
    }

    private int getLoggedInUserId() {
        // Oturum açmış kullanıcının ID'sini döndüren mantığı buraya ekleyin
        return 1; // Örneğin, bu değeri oturumdan veya güvenlik konteksinden alabilirsiniz
    }

    private int getCurrentFilmId() {
        // Şu anki film veya dizi ID'sini döndüren mantığı buraya ekleyin
        return 1; // Örneğin, bu değeri geçerli seçimden veya başka bir kaynaktan alabilirsiniz
    }

    public void loadYorumlar() {
        this.list = this.getDao().getFilmeGirenKısım(currentKullaniciId);
    }

    public void create() throws SQLException {
        if (entity != null && currentDfId > 0 && currentKullaniciId > 0) {
            System.out.println("Creating comment with df_id: " + currentDfId + ", yorum: " + entity.getYorum());
            this.getDao().create(currentDfId, entity.getYorum(), currentKullaniciId);
            entity.setYorum(""); // Yorum ekledikten sonra textarea'yı temizle
            loadYorumlar(); // Yorumları güncelle
        } else {
            System.out.println("Entity is null or df_id/kullanici_id is invalid");
        }
    }

    public void delete(int df_id, int kullanici_id) throws SQLException {
        this.getDao().delete(df_id, kullanici_id);
        loadYorumlar(); // Yorumları güncelle
    }

    public Yorum getEntity() {
        if (entity == null) {
            entity = new Yorum();
        }
        return entity;
    }

    public void setEntity(Yorum entity) {
        this.entity = entity;
    }

    public YorumDao getDao() {
        if (dao == null) {
            dao = new YorumDao();
        }
        return dao;
    }

    public void setDao(YorumDao dao) {
        this.dao = dao;
    }

    public List<Yorum> getList() {
        return list;
    }

    public void setList(List<Yorum> list) {
        this.list = list;
    }

    public List<Yorum> secilenYorum(int kullanici_id) {
        System.out.println(kullanici_id);
        this.list = this.getDao().getFilmeGirenKısım(kullanici_id);
        return list;
    }

    public int getCurrentKullaniciId() {
        return currentKullaniciId;
    }

    public void setCurrentKullaniciId(int currentKullaniciId) {
        this.currentKullaniciId = currentKullaniciId;
    }

    public int getCurrentDfId() {
        return currentDfId;
    }

    public void setCurrentDfId(int currentDfId) {
        this.currentDfId = currentDfId;
    }
}
