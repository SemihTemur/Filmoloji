package dao;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> extends DBConnection {
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity, String tableName, String... columns) {
        try {
            StringBuilder query = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
            for (String column : columns) {
                query.append(column).append(",");
            }
            query.deleteCharAt(query.length() - 1).append(") VALUES (");
            for (int i = 0; i < columns.length; i++) {
                query.append("?,");
            }
            query.deleteCharAt(query.length() - 1).append(")");

            PreparedStatement st = getConnect().prepareStatement(query.toString());
            // set parameters for the PreparedStatement
            // (Note: Reflection can be used here to set the values from the entity)
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<T> getList(String tableName, int page, int pageSize) {
        List<T> entityList = new ArrayList<>();
        int start = ((page - 1) * pageSize);
        int end = start + pageSize;

        try {
            Statement st = getConnect().createStatement();
            String query = "SELECT * FROM " + tableName + " LIMIT " + start + ", " + end;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                // Instantiate the entity and set its properties from the result set
                // (Note: Reflection can be used here to populate the entity fields)
                T entity = entityClass.getDeclaredConstructor().newInstance();
                entityList.add(entity);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return entityList;
    }

    public void delete(T entity, String tableName, int id) {
        try {
            Statement st = getConnect().createStatement();
            String query = "DELETE from " + tableName + " where id=" + id;
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(T entity, String tableName, String... columns) throws SQLException {
        try {
            StringBuilder query = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
            for (String column : columns) {
                query.append(column).append("=?, ");
            }
            query.delete(query.length() - 2, query.length()).append(" WHERE id=?");

            PreparedStatement st = getConnect().prepareStatement(query.toString());
            // set parameters for the PreparedStatement
            // (Note: Reflection can be used here to set the values from the entity)
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
