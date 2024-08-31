package entity;

public class Mesajlar {
    private int mesaj_id;
    private String mesaj;
    private int kullanici_id;

    public int getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(int mesaj_id) {
        this.mesaj_id = mesaj_id;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }
}
