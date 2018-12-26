package com.vysotski.funpay.dao;

import com.vysotski.funpay.dao.impl.ServerDao;
import com.vysotski.funpay.dao.impl.UserDao;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final UserDao userDao = new UserDao();
    private final ServerDao serverDao = new ServerDao();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ServerDao getServerDao() {
        return serverDao;
    }
}
