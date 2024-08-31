package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;

public class PaginationDao extends DBConnection {

    public int countRecords(String tableName) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = this.getConnect();
            String sql = "SELECT COUNT(*) FROM " + tableName;
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return count;
    }

    public ResultSet getPaginatedRecords(String tableName, int offset, int limit) throws SQLException {
        Connection connection = this.getConnect();
        String sql = "SELECT * FROM " + tableName + " LIMIT ? OFFSET ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        return statement.executeQuery();
    }
}
