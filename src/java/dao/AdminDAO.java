/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Admin;
import entity.FilmveDizi;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
public class AdminDAO extends DBConnection{
    
    
    public boolean isValidUser(String username,String password){
        boolean isValid = false;
        jakarta.resource.cci.Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Veritabanı bağlantısı
            

            // SQL sorgusu
            String sql = "SELECT COUNT(*) FROM admin WHERE username = ? AND password = ?";
            statement = getConnect().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            
            resultSet =statement.executeQuery();
            if (resultSet.next()) {
                isValid = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
    
    public Admin getAdmin(String username,String password){
        Admin a= new Admin();
        
        a.setUsername(username);
        a.setPassword(password);
        try{
        
        Statement st = (Statement) this.getConnect().createStatement();
        st.executeUpdate("INSERT INTO admin (username,password) VALUES ('"
                
                + a.getUsername()+ "','"
                + a.getPassword()+ ")");
                

        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return a;
    
    }
    
    public void create(Admin a) {

        try {

            Statement st = (Statement) this.getConnect().createStatement();
            st.executeUpdate("INSERT INTO Admin (username, email,password) VALUES ('"
                    + a.getUsername()+ "','"
                    + a.getEmail()+ "','"
                    + a.getPassword()+ ")");
            
            ResultSet rs =st.getGeneratedKeys();
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Admin> getAdminList(int page, int pageSize) {

        List<Admin> AdminList = new ArrayList<>();

        int start = ((page - 1) * pageSize);
        int son = start + 5;
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query = "SELECT * FROM admin WHERE id BETWEEN " + start + " AND " + son;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                AdminList.add(new Admin(rs.getInt("name"), rs.getString("email"),rs.getString("password")));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return AdminList;
    }
    
      public void delete(Admin a) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE Admin SET id = id - 1 WHERE id > " + a.getId();
            String query1 = "DELETE from admin where id=" + a.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
      
      public void update(Admin a) throws SQLException  {

        Statement st = (Statement) this.getConnect().createStatement();
        String sql = "UPDATE admin SET "
                + "name='" + a.getUsername() + "', "
                + "email='" + a.getEmail()+ "', "
                + "password='" + a.getPassword()+ "', "
                + "WHERE id=" + a.getId();

        st.executeUpdate(sql);

    }
    
    
    
}
