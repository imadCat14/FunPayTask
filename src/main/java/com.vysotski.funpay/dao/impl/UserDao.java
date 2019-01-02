package com.vysotski.funpay.dao.impl;

import com.vysotski.funpay.dao.AbstractDao;
import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.entity.RoleType;
import com.vysotski.funpay.entity.StatusEnum;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.pool.ConnectionPool;
import com.vysotski.funpay.pool.ConnectionPoolException;
import com.vysotski.funpay.pool.ProxyConnection;
import com.vysotski.funpay.util.PasswordDecoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.vysotski.funpay.dao.ColumnName.*;
import static com.vysotski.funpay.dao.SQLQuerry.*;

public class UserDao implements AbstractDao<User> {

    private static final int DEFAULT_USER_ROLE_ID = 2;
    private static final int DEFAULT_USER_STATUS_ID = 1;
//    private static final int BLOCKED_USER_STATUS_ID = 0;

    public User findByLoginAndPassword(String login, String password) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;

        User user = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String currentLogin = resultSet.getString(USER_LOGIN);
                String currentPassword = resultSet.getString(PASSWORD);
                String email = resultSet.getString(MAIL);
                int roleId = resultSet.getInt(ROLE_ID);
                int statusId = resultSet.getInt(STATUS_ID);
                user = new User(currentLogin, currentPassword, email, RoleType.takeRole(roleId), StatusEnum.takeStatus(statusId));
            }
            return user;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    public User findByLogin(String login) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User();

            } else {
                return null;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    @Override
    public List<User> findAll() throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong(USER_ID));
                user.setLogin(resultSet.getString(USER_LOGIN));
                String encodedPassword = resultSet.getString(PASSWORD);
                user.setPassword(PasswordDecoder.decodePassword(encodedPassword));
                user.setEmail(resultSet.getString(MAIL));
                user.setUserRole(RoleType.takeRole(resultSet.getInt(ROLE_ID)));
                user.setUserStatus(StatusEnum.takeStatus(resultSet.getInt(STATUS_ID)));
                users.add(user);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return users;
    }


    @Override
    public boolean findById(long id) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public void create(User user) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setLong(4, DEFAULT_USER_ROLE_ID);
            statement.setInt(5, DEFAULT_USER_STATUS_ID);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    public User blockUserById(long id) throws  DAOException{
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_BLOCK_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User();

            } else {
                return null;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            close(connection);
        }
    }
}
