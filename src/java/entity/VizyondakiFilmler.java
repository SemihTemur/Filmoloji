/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package entity;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class VizyondakiFilmler {

    private FilmveDizi fd;
    private Date olusturulmaTarihi;
    
    
    public VizyondakiFilmler() {
        
    }

    public VizyondakiFilmler(FilmveDizi fd, Date olusturulmaTarihi) {
        this.fd = fd;
        this.olusturulmaTarihi = olusturulmaTarihi;
    }
    
    

    public FilmveDizi getFd() {
        if(fd==null){
            fd= new FilmveDizi();
        }
        return fd;
    }

    public void setFd(FilmveDizi fd) {
        this.fd = fd;
    }

    public Date getOlusturulmaTarihi() {
        return olusturulmaTarihi;
    }

    public void setOlusturulmaTarihi(Date olusturulmaTarihi) {
        this.olusturulmaTarihi = olusturulmaTarihi;
    }
    
    
    
    

}

