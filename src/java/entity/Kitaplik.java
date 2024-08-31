/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author MERVAN
 */
public class Kitaplik {
    private Kullanıcı kullanıcı;
    private FilmveDizi filmdizi;

    public Kitaplik() {
    }

    public Kitaplik(Kullanıcı kullanıcı, FilmveDizi filmdizi) {
        this.kullanıcı = kullanıcı;
        this.filmdizi = filmdizi;
    }

    public Kullanıcı getKullanıcı() {
        if(kullanıcı==null){
            kullanıcı=new Kullanıcı();
        }
        return kullanıcı;
    }

    public void setKullanıcı(Kullanıcı kullanıcı) {
        this.kullanıcı = kullanıcı;
    }

    public FilmveDizi getFilmdizi() {
        if(filmdizi ==null){
            filmdizi=new FilmveDizi();
        }
        return filmdizi;
    }

    public void setFilmdizi(FilmveDizi filmdizi) {
        this.filmdizi = filmdizi;
    }
    
    
    
    
    
}
