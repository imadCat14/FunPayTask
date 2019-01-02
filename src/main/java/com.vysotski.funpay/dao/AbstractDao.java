package com.vysotski.funpay.dao;

import com.vysotski.funpay.entity.Entity;
import com.vysotski.funpay.pool.ConnectionPool;
import com.vysotski.funpay.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AbstractDao<T extends Entity> {
    Logger logger = LogManager.getLogger();
    List<T> findAll() throws DAOException;
    boolean findById(long id) throws DAOException;


    boolean delete(long id);
    boolean delete(T entity);
    void create(T entity) throws DAOException;
    default void close(Statement statement){
        try{
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Unable to close connection");
        }
    }
    default void close(ProxyConnection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
