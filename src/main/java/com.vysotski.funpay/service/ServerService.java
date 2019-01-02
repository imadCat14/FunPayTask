package com.vysotski.funpay.service;

import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.dao.DAOFactory;
import com.vysotski.funpay.dao.impl.ServerDao;
import com.vysotski.funpay.entity.Chronicle;
import com.vysotski.funpay.entity.Mark;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.validator.ServerValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServerService {
    public List<Server> selectAll() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers;
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
        List<Review> reviews;
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
            serverDao.findById(serverId);
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

//    public List<Server> addServer(String serverName, String serverDescription, long chronicleId) throws ServiceException{
//        DAOFactory daoFactory = DAOFactory.getInstance();
//        ServerDao serverDao = daoFactory.getServerDao();
//        List<Server> servers = new ArrayList<>();
//        try {
//            if (serverDao.findServerByName(serverName) == null) {
//                Server server = new Server();
//                server.setServerName(serverName);
//                server.setDescription(serverDescription);
//                server.getChronicle().setChronicleId(chronicleId);
//                serverDao.create(server);
//                servers.add(server);
//            }
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//        return servers;
//    }

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
        List<Mark> marks;
        try {
            marks = serverDao.findServerMarks(serverId);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return marks;
    }

    public List<Server> createServer(String serverName, String serverChronicle, String serverDescription) throws ServiceException {
        if (!ServerValidator.validateAlienData(serverName, serverChronicle, serverDescription)) {
            throw new ServiceException("Incorrect data for new Server");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers = new ArrayList<>();

        try {
            addNewChronicle(serverChronicle);
            List<Chronicle> chronicles = serverDao.findChronicleByName(serverChronicle);
            if (!serverDao.findServerByName(serverName)) {
                Server server = new Server();
                server.setServerName(serverName);
                serverDao.findChronicleByName(serverChronicle);
                Chronicle chronicle = new Chronicle(chronicles.get(0).getChronicleId(), chronicles.get(0).getChronicleName());
                server.setChronicle(chronicle);
                server.setDescription(serverDescription);
                serverDao.create(server);
                servers.add(server);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return servers;
    }

    private void addNewChronicle(String serverChronicle) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        try {
            List<Chronicle> homelands = serverDao.findChronicleByName(serverChronicle);
            if (homelands.isEmpty()) {
                serverDao.createChronicle(new Chronicle(serverChronicle));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
