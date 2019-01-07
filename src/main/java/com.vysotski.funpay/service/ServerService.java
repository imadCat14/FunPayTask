package com.vysotski.funpay.service;

import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.dao.DAOFactory;
import com.vysotski.funpay.dao.impl.ServerDao;
import com.vysotski.funpay.dao.impl.UserDao;
import com.vysotski.funpay.entity.Chronicle;
import com.vysotski.funpay.entity.Mark;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.validator.ReviewValidator;
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
//    markServer

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

    public List<Review> findReviews(long serverId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Review> reviews = new ArrayList<>();
        try {
            reviews = serverDao.findServerReviews(serverId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    public Review addReview( String reviewText,long serverId,long userId) throws ServiceException {
        if (!ReviewValidator.validateReviewData(reviewText)) {
            throw new ServiceException("Incorrect review data");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        Review currentReview = new Review();
        try {
            currentReview.setUserId(userId);
            currentReview.setServerId(serverId);
            currentReview.setReviewText(reviewText);
            currentReview.setReviewDate(Date.valueOf(LocalDate.now()));
            serverDao.createReview(currentReview);

        }catch (DAOException e){
            throw new ServiceException(e);
        }
        return currentReview;
    }

    public List<Server> findServersByNameFragment(String serverName) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers = new ArrayList<>();
        try {
            servers = serverDao.findServerInformationByName(serverName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return servers;
    }

    public List<Server> findServerByName(String serverName) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers = new ArrayList<>();
        try {
            servers = serverDao.takeServerInformationByName(serverName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return servers;
    }



//    addmark
    public Mark markServer(long userID, long serverId, int mark) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        Mark currentMark = new Mark();
        try {
            if (!serverDao.findMarkByUserIdAndServerId(userID, serverId)) {
                currentMark.setUserId(userID);
                currentMark.setServerId(serverId);
                currentMark.setMark(mark);
                serverDao.insertMark(currentMark);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return currentMark;
    }

    public void updateDescription(String serverDescription, String serverName) throws ServiceException {
        if (!ServerValidator.validateServerDescription(serverDescription)) {
            throw new ServiceException("Incorrect data for server description");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();

        try {
            serverDao.updateDescription(serverDescription, serverName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Review> findUserReviews(long userId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        List<Review> reviews = new ArrayList<>();
        try {
            reviews = userDao.findUserReviews(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reviews;
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

    public List<Server> createServer(String serverName, String chronicleName, String serverDescription) throws ServiceException {
        if (!ServerValidator.validateServerData(serverName, chronicleName, serverDescription)) {
            throw new ServiceException("Incorrect data for new Server");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ServerDao serverDao = daoFactory.getServerDao();
        List<Server> servers = new ArrayList<>();

        try {
            addNewChronicle(chronicleName);
            List<Chronicle> chronicles = serverDao.findChronicleByName(chronicleName);
            if (!serverDao.findServerByName(serverName)) {
                Server server = new Server();
                server.setServerName(serverName);
                serverDao.findChronicleByName(chronicleName);
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
            List<Chronicle> chronicles = serverDao.findChronicleByName(serverChronicle);
            if (chronicles.isEmpty()) {
                serverDao.createChronicle(new Chronicle(serverChronicle));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
