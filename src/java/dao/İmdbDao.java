/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Platform;
import entity.İmdb;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author semih
 */
public class İmdbDao extends DBConnection {
    
       public void create(İmdb idf,int fd_id) throws SQLException {
        PreparedStatement pst = this.getConnect().prepareStatement("INSERT INTO imdb (imdb,df_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, idf.getImdb());
        pst.setInt(2, fd_id);

        pst.executeUpdate();
    }
}
