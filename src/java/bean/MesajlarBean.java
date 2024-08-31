package bean;

import dao.MesajlarDao;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import entity.Mesajlar;

@Named(value = "mesajlarBean")
@SessionScoped
public class MesajlarBean implements Serializable {

    private Mesajlar entity;
    private MesajlarDao dao;
    private List<Mesajlar> list;

    private int currentKullaniciId; // Oturum açmış kullanıcının ID'si

    public MesajlarBean() {
        entity = new Mesajlar();
    }

    @PostConstruct
    public void init() {
        // Geçerli kullanıcı ID'sini burada ayarlayın
        this.currentKullaniciId = getLoggedInUserId();
        loadMesajlar(); // Mesajları yükle
    }

    private int getLoggedInUserId() {
        // Oturum açmış kullanıcının ID'sini döndüren mantığı buraya ekleyin
        return 1; // Örneğin, bu değeri oturumdan veya güvenlik konteksinden alabilirsiniz
    }

    public void loadMesajlar() {
        this.list = this.getDao().getMesajlar(currentKullaniciId);
    }

    public void create() throws SQLException {
        if (entity != null && currentKullaniciId > 0) {
            System.out.println("Creating message with kullanici_id: " + currentKullaniciId + ", mesaj: " + entity.getMesaj());
            this.getDao().create(currentKullaniciId, entity.getMesaj());
            entity.setMesaj(""); // Mesaj ekledikten sonra textarea'yı temizle
            loadMesajlar(); // Mesajları güncelle
        } else {
            System.out.println("Entity is null or kullanici_id is invalid");
        }
    }

    public void delete(int mesaj_id) throws SQLException {
        this.getDao().delete(mesaj_id);
        loadMesajlar();
    }

    public Mesajlar getEntity() {
        if (entity == null) {
            entity = new Mesajlar();
        }
        return entity;
    }

    public void setEntity(Mesajlar entity) {
        this.entity = entity;
    }

    public MesajlarDao getDao() {
        if (dao == null) {
            dao = new MesajlarDao();
        }
        return dao;
    }

    public void setDao(MesajlarDao dao) {
        this.dao = dao;
    }

    public List<Mesajlar> getList() {
        return list;
    }

    public void setList(List<Mesajlar> list) {
        this.list = list;
    }

    public List<Mesajlar> getMesajlar(int kullanici_id) {
        System.out.println(kullanici_id);
        this.list = this.getDao().getMesajlar(kullanici_id);
        return list;
    }

    public int getCurrentKullaniciId() {
        return currentKullaniciId;
    }

    public void setCurrentKullaniciId(int currentKullaniciId) {
        this.currentKullaniciId = currentKullaniciId;
    }
}
