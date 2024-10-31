package com.DBMSproj.app.daotemplates;
import java.util.*;
import java.sql.*;

public abstract class TableDAO<T> {
    private Connection connection;
    private String tableName;
    private String primaryKey;
    
    private String[] foreignKeys;
    private String[] columns;

    public TableDAO(
        Connection connection, 
        String tableName, 
        String primaryKey, 
        String[] foreignKeys, 
        String[] columns) {
        this.connection = connection;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.foreignKeys = foreignKeys;
        this.columns = columns;
    }

    public abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;//since the map will be different for each entity
    //we use abstract method to be implemented in inherited ientity

    public List<T> findById(Long id) {
        ArrayList<T> resultSet = new ArrayList<>();
        try {
            String sql = "Select * from "+tableName+" where "+primaryKey+" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                T entity = mapResultSetToEntity(rs);
                resultSet.add(entity);
            }
        } catch(SQLException e) {}
        return resultSet;
    }

}
