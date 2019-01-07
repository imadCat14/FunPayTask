package com.vysotski.funpay.dao.impl;

import com.vysotski.funpay.dao.AbstractDao;
import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.entity.Review;
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
import static com.vysotski.funpay.dao.SQLQuery.*;

public class UserDao implements AbstractDao<User> {

    private static final int DEFAULT_USER_ROLE_ID = 2;
    private static final int DEFAULT_USER_STATUS = 1;
//    private static final int BLOCKED_USER_STATUS = 0;

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
                String email = resultSet.getString(EMAIL);
                int roleId = resultSet.getInt(ROLE_ID);
                int status = resultSet.getInt(STATUS);
                user = new User(currentLogin, currentPassword, email, RoleType.takeRole(roleId), StatusEnum.takeStatus(status));
            }
            return user;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    public boolean findByLogin(String login) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            return (resultSet.next()) ;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public User findByLoginForReview(String login) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String currentLogin = resultSet.getString(USER_LOGIN);
                long currentUserId = resultSet.getLong(USER_ID);
                user = new User(currentUserId, currentLogin);
            }
            return user;
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
                user.setUserID(resultSet.getLong(USER_ID));
                user.setLogin(resultSet.getString(USER_LOGIN));
                String encodedPassword = resultSet.getString(PASSWORD);
                user.setPassword(PasswordDecoder.decodePassword(encodedPassword));
                user.setEmail(resultSet.getString(EMAIL));
                user.setUserRole(RoleType.takeRole(resultSet.getInt(ROLE_ID)));
                user.setUserStatus(StatusEnum.takeStatus(resultSet.getInt(STATUS)));
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
            statement.setInt(5, DEFAULT_USER_STATUS);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }


    public void blockUserById(long id) throws  DAOException{
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_BLOCK_USER_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            close(connection);
        }
    }

    public void updateUserStatus(String selectedStatus, String selectedUser) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER_STATUS);
            statement.setInt(1, Integer.parseInt(selectedStatus));
            statement.setString(2, selectedUser);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public User update(User entity) {
        return null;
    }

    public List<Review> findUserReviews(long userId) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Review> reviews = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWS);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setServerName(resultSet.getString(SERVER_NAME));
                review.setLogin(resultSet.getString(USER_LOGIN));
                review.setReviewText(resultSet.getString(TEXT_REVIEW));
                review.setReviewDate(resultSet.getDate(DATE_REVIEW));
                reviews.add(review);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return reviews;
    }
}
