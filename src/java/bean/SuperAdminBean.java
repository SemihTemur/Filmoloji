/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package bean;

import dao.AdminDAO;
import dao.SuperAdminDAO;
import entity.Admin;
import entity.FilmveDizi;
import entity.SuperAdmin;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MERVAN
 */
@Named(value = "superAdminBean")
@SessionScoped
public class SuperAdminBean implements Serializable {

    private SuperAdmin entity;
    private SuperAdminDAO dao;
    private List<SuperAdmin> list;

    private String username;
    private String password;

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public SuperAdminBean() {
    }

    public String login22() {
        SuperAdmin superadmin = this.getDao().getSuperAdmin(this.username, this.password);
        if ((getUsername().equals("semih")) && (getPassword().equals("345"))) {
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", admin);
            return "/SuperAdminPaneli/SuperAdminIslemleri?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("kullanıcı adı veya şifre hatalı"));
            return "/SuperAdminPaneli/SuperAdmin?faces-redirect=true";
        }
    }

    public void next() {
        if (this.page == this.pageCount) {
            this.page = 1;
        } else {
            this.page++;
        }

    }

    public void prev() {
        if (this.page == 1) {
            this.page = this.pageCount;
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

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new SuperAdmin();
    }

    public void update() throws SQLException {
        this.getDao().update(entity);
        entity = new SuperAdmin();
    }

    public void delete(SuperAdmin sa) {
        this.getDao().delete(sa);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SuperAdmin getEntity() {
        if (this.entity == null) {
            this.entity = new SuperAdmin();
        }
        return entity;
    }

    public void setEntity(SuperAdmin entity) {
        this.entity = entity;
    }

    public SuperAdminDAO getDao() {
        if (this.dao == null) {
            this.dao = new SuperAdminDAO();
        }
        return dao;
    }

    public void setDao(SuperAdminDAO dao) {
        this.dao = dao;
    }

    public List<SuperAdmin> getList() {
        this.list = getDao().getAdminList();
        return list;
    }

    public void setList(List<SuperAdmin> list) {
        this.list = list;
    }

    public String navigateToGuncellePage(int id) {
        entity = new SuperAdmin();
        entity.setId(id);
        return "/SuperAdminPaneli/AdminEkle?faces-redirect=true";
    }

}
