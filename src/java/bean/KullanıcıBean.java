/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.KullanıcıDAO;
import entity.Kullanıcı;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.resource.cci.Connection;
import jakarta.resource.cci.ResultSet;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Halil
 */
@Named(value = "kullanıcıBean")
@SessionScoped
public class KullanıcıBean implements Serializable {

    private Kullanıcı entity;
    private KullanıcıDAO dao;
    private List<Kullanıcı> list;
    String link;
    private int kullanici_id;

    public String login() {
        if (getDao().isValidUser(this.entity.getName(), entity.getPassword())) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getSessionMap().put("validUser", entity);
            try {
                ExternalContext externalContext = fc.getExternalContext();
                externalContext.redirect("AnaSayfa.xhtml");
                return "AnaSayfa.xhtml";
            } catch (IOException e) {
                System.out.println("Şifre: " + entity.getPassword());
                e.printStackTrace();
            }
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı adı veya şifre hatalı"));
            System.out.println("Şifre hatalı");
            return null;
        }
    }

    public String logout() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("validUser", null);
         ExternalContext externalContext = fc.getExternalContext();
                externalContext.redirect("AnaSayfa.xhtml");
                return "AnaSayfa.xhtml";
    }

    public KullanıcıBean() {
    }

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }

    }

    public void prev() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDao().count() / (double) pageSize);
        return pageCount;
    }

    public KullanıcıBean(Kullanıcı entity, KullanıcıDAO dao, List<Kullanıcı> list, int pageCount) {
        this.entity = entity;
        this.dao = dao;
        this.list = list;
        this.pageCount = pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void delete(Kullanıcı c) {
        this.getDao().delete(c);
    }

    public void create() {
        this.getDao().create(this.entity);
        this.entity = new Kullanıcı();
    }

    public Kullanıcı getEntity() {
        if (entity == null) {
            entity = new Kullanıcı();
        }
        return entity;
    }

    public void setEntity(Kullanıcı entity) {
        this.entity = entity;
    }

    public KullanıcıDAO getDao() {
        if (this.dao == null) {
            this.dao = new KullanıcıDAO();
        }
        return dao;
    }

    public void setDao(KullanıcıDAO dao) {
        this.dao = dao;
    }

    public List<Kullanıcı> getList() {
        this.list = this.getDao().getKullanıcıList();
        return list;
    }

    public void setList(List<Kullanıcı> list) {
        this.list = list;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getKullanici_id() {
        this.kullanici_id = this.getDao().newFilmDiziId;
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = this.getDao().newFilmDiziId;
        System.out.println("lan emmi");
    }

}
