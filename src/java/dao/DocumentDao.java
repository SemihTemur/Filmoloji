package dao;

import entity.Document;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
import java.sql.PreparedStatement;

public class DocumentDao extends DBConnection {

    public void create(Document d,int fd_id) throws SQLException {
        String query = "INSERT INTO documents (filepath,filename,filetype,df_id) values(?,?,?,?)";
        PreparedStatement pst = this.getConnect().prepareStatement(query);
        pst.setString(1, d.getFilePath());
        pst.setString(2, d.getFileName());
        pst.setString(3, d.getFileType());
        pst.setInt(4, fd_id);
        
        
        
        

        pst.executeUpdate();
    }

//    public Document findById(int id) {
//
//        Document dlist = null;
//        try {
//            String query = "SELECT * FROM document where id=" + id;
//            PreparedStatement pst = this.getConnect().prepareStatement(query);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                dlist = new Document(rs.getString("path"), rs.getString("name"), rs.getString("type"));
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return dlist;
//    }

}

