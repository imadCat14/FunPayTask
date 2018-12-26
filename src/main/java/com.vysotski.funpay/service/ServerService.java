package com.vysotski.funpay.service;

import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.dao.DAOFactory;
import com.vysotski.funpay.dao.impl.ServerDao;
import com.vysotski.funpay.entity.Mark;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServerService {
    public List<Server> selectAll() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers = new ArrayList<>();
        try {
            servers = serverDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return servers;
    }
    public List<Review>findReviews(long serverId) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Review> reviews=new ArrayList<>();
        try {
            reviews = serverDao.findServerReviews(serverId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return reviews;
    }

    public Server defineServer(long serverId) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        Server server = new Server();
        try {
            server = serverDao.findById(serverId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return server;
    }

    public Review addReview( String textReview,long serverId,long userId) throws ServiceException {
        if (textReview==null) {//TODO check Length
            throw new ServiceException("Incorrect review data");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        Review currentReview = new Review();
        try {
            currentReview.setUserId(userId);
            currentReview.setServerId(serverId);
            currentReview.setTextReview(textReview);
            currentReview.setDateReview(Date.valueOf(LocalDate.now()));
            serverDao.createReview(currentReview);

        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return currentReview;
    }

    public List<Server> addServer(String serverName, String serverDescription, long chronicleId) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers = new ArrayList<>();
        try {
            if (serverDao.findByName(serverName) == null) {
                Server server = new Server();
                server.setServerName(serverName);
                server.setDescription(serverDescription);
                server.getChronicle().setChronicleId(chronicleId);
                serverDao.create(server);
                servers.add(server);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return servers;
    }

    public Mark addMarkServer(long userId, long serverId, int serverMark) throws ServiceException {
        if (serverMark==0) { //TODO nado range marks postavit'
            throw new ServiceException("Incorrect mark data");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        Mark currentMark = new Mark();
        try {
            currentMark.setUserId(userId);
            currentMark.setServerId(serverId);
            serverDao.markServer(currentMark);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return currentMark;
    }

    public List<Mark> findMarks(long serverId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Mark> marks=new ArrayList<>();
        try {
            marks = serverDao.findServerMarks(serverId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return marks;
    }
}
