/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Admin;
import entity.FilmveDizi;
import entity.SuperAdmin;
import jakarta.resource.cci.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author MERVAN
 */
public class SuperAdminDAO extends DBConnection{
    
    private Connection connection;
    
    public SuperAdmin getSuperAdmin(String username,String password){
        SuperAdmin a= new SuperAdmin();
        
        a.setUsername(username);
        a.setPassword(password);
        try{
        
        Statement st = (Statement) this.getConnect().createStatement();
        st.executeUpdate("INSERT INTO admin (username,password,email) VALUES ('"
                
                + a.getUsername()+ "','"
                + a.getPassword()+ "','"
                + a.getEmail()+ ")");
                

        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return a;
    
    }
    
    public List<SuperAdmin> getAdminList() {

        List<SuperAdmin> adminList = new ArrayList<>();

        
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query = "SELECT * FROM admin " ;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                adminList.add(new SuperAdmin(rs.getInt("admin_id"), rs.getString("username"), rs.getString("email"), rs.getString("password")));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return adminList;
    }
    
    public void create(SuperAdmin sa) {

        try {

            Statement st = (Statement) this.getConnect().createStatement();
            st.executeUpdate("INSERT INTO admin (username, password, email) VALUES ('"
                    + sa.getUsername()+ "','"
                    + sa.getPassword()+ "','"
                    + sa.getEmail()+ "')");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(SuperAdmin sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE admin SET admin_id = admin_id - 1 WHERE admin_id > " + sa.getId();
            String query1 = "DELETE from admin where admin_id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
      
      public void update(SuperAdmin sa) throws SQLException  {
          
        
        Statement st = (Statement) this.getConnect().createStatement();
        
        String sql = "UPDATE admin SET "
                + "username='" + sa.getUsername() + "', "
                + "email='" + sa.getEmail()+ "', "
                + "password='" + sa.getPassword()+ "', "
                + "WHERE id=" + sa.getId();

        st.executeUpdate(sql);
        

    }
      
      public int count() {

        int count = 0;

        List<SuperAdmin> adminList = new ArrayList<>();

        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query = "select count(id) as film_count from admin";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt("film_count");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
      
      
    
}
