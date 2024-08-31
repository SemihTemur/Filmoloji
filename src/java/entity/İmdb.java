package entity;

public class İmdb {
    private int id;
    private String imdb;

    public İmdb( String imdb,int id){
        this.imdb = imdb;
        this.id=id;
    }

    public İmdb(String imdb) {
        this.imdb = imdb;
    }
    

    public İmdb() {
    }
    
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

}

