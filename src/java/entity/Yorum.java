package entity;

public class Yorum {
    private int df_id;
    private String yorum;
    private int kullanici_id;

    public int getDf_id() {
        return df_id;
    }

    public void setDf_id(int df_id) {
        this.df_id = df_id;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }
}
