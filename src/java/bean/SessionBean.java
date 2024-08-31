/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import dao.AdminDAO;
import dao.FilmveDiziDao;
import entity.Admin;
import entity.FilmveDizi;
import entity.Kullanıcı;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MERVAN
 */

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable{
    
    private String username;
    private String password;
    
    
    private String username2;
    private  String password2;
    
    private Kullanıcı kullanıcı;
    private Admin entity;
    private AdminDAO ldao;
    private List<Admin> list;

    public SessionBean() {
    }

    public SessionBean(String username, String password, Admin entity, AdminDAO ldao, List<Admin> list) {
        this.username = username;
        this.password = password;
        this.entity = entity;
        this.ldao = ldao;
        this.list = list;
    }
    
    
/*    
    public String login(){
        Admin admin = this.getLdao().getAdmin(this.username, this.password);
        if((getUsername2().equals("mervan")) && (getPassword2().equals("123"))){
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", admin);
        return "/AdminPaneli/AdminIslemleri?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("kullanıcı adı veya şifre hatalı"));
            return"/AdminPaneli/Admin?faces-redirect=true";
        }
    }
*/    
    public String login() {
        if (getLdao().isValidUser(entity.getUsername(),entity.getPassword())) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getSessionMap().put("validUser2", this.entity);
            try {
                
                ExternalContext externalContext = fc.getExternalContext();
                externalContext.redirect("AdminIslemleri.xhtml");
                return "AdminIslemleri.xhtml";
            } catch (IOException e) {
                // Yönlendirme hatası
                e.printStackTrace();
                // İstediğiniz şekilde hatayı yönetebilirsiniz, örneğin bir mesaj gösterebilirsiniz.
            }
            return null; // Yönlendirme işlemini FacesContext'e bırakmak için null dön
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı adı veya şifre hatalı"));
            System.out.println("Şifre hatalı");
            return null; // Giriş başarısız olduğunda da sayfada kalmak için null dön
        }

    }
    
    public void create(){
        this.getLdao().create(entity);
        this.entity=new Admin();
    }
    
    
    public void update() throws SQLException  {
        this.getLdao().update(entity);
        entity=new Admin();
    }

    public void delete(Admin a) {
        this.getLdao().delete(a);
    }

    public Kullanıcı getKullanıcı() {
        if(kullanıcı==null){
        kullanıcı = new Kullanıcı();
        }
        return kullanıcı;
    }

    public void setKullanıcı(Kullanıcı kullanıcı) {
        this.kullanıcı = kullanıcı;
    }

    
    
    
    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Admin getEntity() {
        if (this.entity == null) {
            this.entity = new Admin();
        }
        return entity;
    }

    public void setEntity(Admin entity) {
        this.entity = entity;
    }


    public List<Admin> getList() {
        return list;
    }

    public void setList(List<Admin> list) {
        this.list = list;
    }
    
    

    public AdminDAO getLdao() {
        if(this.ldao==null){
            this.ldao = new AdminDAO();
        }
        return ldao;
    }

    public void setLdao(AdminDAO ldao) {
        this.ldao = ldao;
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
    
    
    
    
    
    
    
    
}
