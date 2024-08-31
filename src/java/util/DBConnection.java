package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBConnection {

    public Connection getConnect() {
        Connection c = null;
        try {
           
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FilmveDizi", "postgres", "123456");
            System.out.println("Basarili");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;

    }

}
