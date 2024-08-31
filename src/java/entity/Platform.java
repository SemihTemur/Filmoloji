package entity;

public class Platform {

    private int id;
    private String platform_adi;

    public Platform() {
    }

    public Platform(int id, String platform_adi) {
        this.id = id;
        this.platform_adi = platform_adi;
    }
    
    public Platform( String platform_adi) {
        this.platform_adi = platform_adi;
    }
    
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform_adi() {
        return platform_adi;
    }

    public void setPlatform_adi(String platform_adi) {
        this.platform_adi = platform_adi;
    }

}

