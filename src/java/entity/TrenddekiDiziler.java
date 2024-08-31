/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author semih
 */
public class TrenddekiDiziler {

    private FilmveDizi fd;
    private Date olusturulmaTarihi;
    

    public TrenddekiDiziler() {
    }

    public TrenddekiDiziler(FilmveDizi fd, Date olusturulmaTarihi) {
        this.fd = fd;
        this.olusturulmaTarihi = olusturulmaTarihi;
    }
    
    

    public FilmveDizi getFd() {
        if (fd == null) {
            fd = new FilmveDizi();
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
