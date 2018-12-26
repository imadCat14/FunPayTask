package com.vysotski.funpay.dao.impl;

import com.vysotski.funpay.dao.AbstractDao;
import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.entity.Chronicle;
import com.vysotski.funpay.entity.Mark;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.pool.ConnectionPool;
import com.vysotski.funpay.pool.ConnectionPoolException;
import com.vysotski.funpay.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.vysotski.funpay.dao.ColumnName.*;
import static com.vysotski.funpay.dao.SQLQuerry.*;

public class ServerDao implements AbstractDao<Server> {
//izmenit' TODO

    @Override
    public List<Server> findAll() throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Server> aliens = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_SERVERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Server server = new Server();
                server.setServerId(resultSet.getLong(SERVER_ID));
                server.setServerName(resultSet.getString(SERVER_NAME));
                server.setDescription(resultSet.getString(SERVER_DESCRIPTION));
                server.setChronicle(new Chronicle(resultSet.getLong(CHRONICLE_ID), resultSet.getString(CHRONICLE_NAME)));
                aliens.add(server);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            close(connection);
        }
        return aliens;
    }

    @Override//TODO Return List<Server>
    public Server findById(long id) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_SERVER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            } else {
                return new Server();
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            UserDao userDao = new UserDao();
            userDao.close(statement);
            userDao.close(connection);
        }
    }

    public Server findByName(String serverName) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        User user = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_SERVER_BY_NAME);
            statement.setString(1, serverName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            } else {
                return new Server();
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            UserDao userDao = new UserDao();
            userDao.close(statement);
            userDao.close(connection);
        }
    }

    @Override
    public boolean delete(long id) {
//        ProxyConnection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = ConnectionPool.getInstance().takeConnection();
//            statement = connection.prepareStatement(SQL_DELETE_ALIEN_BY_ID);
//            statement.setLong(1, id);
//            resultSet = statement.executeQuery();
//            if (!resultSet.next()) {
//                return null;
//            } else {
//                return new Alien();
//            }
//        } catch (ConnectionPoolException | SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            UserDaoImpl userDAO = new UserDaoImpl();
//            userDAO.close(statement);
//            userDAO.close(connection);
//        }

        return false;
    }

    @Override
    public boolean delete(Server entity) {
        return false;
    }

    @Override
    public void create(Server server) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_NEW_SERVER);
            statement.setString(2, server.getServerName());
            statement.setString(3, server.getDescription());
            statement.setLong(1, server.getChronicle().getChronicleId());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Server update(Server entity) {
        return null;
    }


    public List<Review> findServerReviews(long serverId) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Review> reviews = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_SERVER_REVIEWS);
            statement.setLong(1, serverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                review.setServerId(resultSet.getLong(SERVER_ID));
                review.setUserId(resultSet.getLong(USER_ID));
                review.setTextReview(resultSet.getString(TEXT_REVIEW));
                review.setDateReview(resultSet.getDate(DATE_REVIEW));
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

    public void createReview(Review review) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_REVIEW);
            statement.setLong(1, review.getServerId());
            statement.setLong(2, review.getUserId());
            statement.setString(3, review.getTextReview());
            statement.setDate(4, review.getDateReview());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public void markServer(Mark mark) throws DAOException{
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_MARK_SERVER);
            statement.setInt(1, mark.getMark());
            statement.setLong(2, mark.getUserId());
            statement.setLong(3, mark.getServerId());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public List<Mark> findServerMarks(long serverId) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Mark> marks = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_SERVER_MARKS);
            statement.setLong(1, serverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mark mark = new Mark();
                mark.setServerId(resultSet.getLong(SERVER_ID));
                mark.setUserId(resultSet.getLong(USER_ID));
                marks.add(mark);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return marks;
    }

    public void createMark(Mark currentMark) {
    }
}
