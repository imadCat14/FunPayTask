package com.vysotski.funpay.dao.impl;

import com.vysotski.funpay.dao.AbstractDao;
import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.entity.Chronicle;
import com.vysotski.funpay.entity.Mark;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.pool.ConnectionPool;
import com.vysotski.funpay.pool.ConnectionPoolException;
import com.vysotski.funpay.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.vysotski.funpay.dao.ColumnName.*;
import static com.vysotski.funpay.dao.SQLQuery.*;

public class ServerDao implements AbstractDao<Server> {

    @Override
    public List<Server> findAll() throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Server> servers = new ArrayList<>();
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
                server.setAverageMark(resultSet.getDouble(AVERAGE_MARK));
                servers.add(server);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            close(connection);
        }
        return servers;
    }

    @Override
    public boolean findById(long id) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_SERVER_BY_ID);
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

    public boolean findMarkByUserIdAndServerId(long userID,long serverId) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_SERVER_MARK_FROM_USER);
            statement.setLong(1, userID);
            statement.setLong(2, serverId);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next());
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public boolean findServerByName(String serverName) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_SERVER_BY_NAME);
            statement.setString(1, serverName);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next());
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
            statement.setString(1, server.getServerName());
            statement.setString(2, server.getDescription());
            statement.setLong(3, server.getChronicle().getChronicleId());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
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

    public void createReview(Review review) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_REVIEW);
            statement.setLong(1, review.getServerId());
            statement.setLong(2, review.getUserId());
            statement.setString(3, review.getReviewText());
            statement.setDate(4, review.getReviewDate());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public void insertMark(Mark mark) throws DAOException{
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_MARK_SERVER);
            statement.setLong(1, mark.getUserId());
            statement.setLong(2, mark.getServerId());
            statement.setInt(3,mark.getMark());

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

    public Chronicle createChronicle(Chronicle chronicle) throws DAOException{
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_INSERT_CHRONICLE);
            statement.setString(1, chronicle.getChronicleName());
            statement.executeUpdate();
            return chronicle;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    public List<Chronicle> findChronicleByName(String homelandName) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Chronicle> chronicles = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_CHRONICLE_ID_BY_CHRONICLE_NAME);
            statement.setString(1, homelandName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Chronicle chronicle = new Chronicle();
                chronicle.setChronicleName(homelandName);
                chronicle.setChronicleId(resultSet.getLong("chronicleId"));
                chronicles.add(chronicle);
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }

        return chronicles;
    }

    public List<Server> findServerInformationByName(String serverName) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Server>servers=new ArrayList<>();
        Server server=null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_TAKE_SERVER_INFORMATION_BY_NAME);
            statement.setString(1, '%'+serverName+'%');
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                server = new Server();
                server.setServerId(resultSet.getLong(SERVER_ID));
                server.setServerName(resultSet.getString(SERVER_NAME));

                server.setDescription(resultSet.getString(SERVER_DESCRIPTION));
                server.setChronicle(new Chronicle(resultSet.getLong(CHRONICLE_ID), resultSet.getString(CHRONICLE_NAME)));
                server.setAverageMark(resultSet.getDouble(AVERAGE_MARK));
                servers.add(server);

            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            close(connection);
        }
        return servers;
    }

    public List<Server> takeServerInformationByName(String serverName) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        List<Server>servers=new ArrayList<>();
        Server server=null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_TAKE_SERVER_INFORMATION_BY_NAME);
            statement.setString(1, serverName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                server = new Server();
                server.setServerId(resultSet.getLong(SERVER_ID));
                server.setServerName(resultSet.getString(SERVER_NAME));

                server.setDescription(resultSet.getString(SERVER_DESCRIPTION));
                server.setChronicle(new Chronicle(resultSet.getLong(CHRONICLE_ID), resultSet.getString(CHRONICLE_NAME)));
                server.setAverageMark(resultSet.getDouble(AVERAGE_MARK));
                servers.add(server);

            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            close(connection);
        }
        return servers;
    }

    public void updateDescription(String serverDescription, String serverName) throws DAOException {
        ProxyConnection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_SERVER_DESCRIPTION);
            statement.setString(1, serverDescription);
            statement.setString(2, serverName);
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException();
        } finally {
            close(statement);
            close(connection);
        }
    }
}
