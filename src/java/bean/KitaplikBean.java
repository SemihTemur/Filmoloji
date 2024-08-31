/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.KitaplikDAO;
import dao.KullanıcıDAO;
import entity.Kitaplik;
import entity.Kullanıcı;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MERVAN
 */
@Named(value = "kitaplikBean")
@SessionScoped
public class KitaplikBean implements Serializable {

    private Kitaplik entity;
    private KitaplikDAO dao;
    private List<Kitaplik> list;

    public KitaplikBean() {
    }

    public KitaplikBean(Kitaplik entity, KitaplikDAO dao, List<Kitaplik> list) {
        this.entity = entity;
        this.dao = dao;
        this.list = list;
    }

    public void create(int fd_id, int kullanici_id) throws SQLException {
        this.getDao().create(fd_id, kullanici_id);
    }
    
    public void delete(int fd_id,int kullanici_id) throws SQLException{
        this.getDao().delete(fd_id,kullanici_id);
    }

    public Kitaplik getEntity() {
        if (entity == null) {
            entity = new Kitaplik();
        }
        return entity;
    }

    public void setEntity(Kitaplik entity) {
        this.entity = entity;
    }

    public KitaplikDAO getDao() {
        if (dao == null) {
            dao = new KitaplikDAO();
        }
        return dao;
    }

    public void setDao(KitaplikDAO dao) {
        this.dao = dao;
    }

    public List<Kitaplik> getList() {
        return list;
    }

    public void setList(List<Kitaplik> list) {
        this.list = list;
    }

    // Method to fetch selected films based on user ID
    public List<Kitaplik> secilenKitaplik(int kullanici_id) {
        System.out.println(kullanici_id);
        this.list = this.getDao().getFilmeGirenKısım(kullanici_id);
        return list;
    }

}
