/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Platform;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;

/**
 *
 * @author semih
 */
public class PlatformDao extends DBConnection {

    public void create(Platform pf,int fd_id) throws SQLException {
        PreparedStatement pst = this.getConnect().prepareStatement("INSERT INTO platform (platform,df_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, pf.getPlatform_adi());
        pst.setInt(2, fd_id);

        pst.executeUpdate();
    }

}

