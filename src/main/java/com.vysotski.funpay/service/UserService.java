package com.vysotski.funpay.service;

import com.vysotski.funpay.dao.DAOException;
import com.vysotski.funpay.dao.DAOFactory;
import com.vysotski.funpay.dao.impl.UserDao;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.util.PasswordEncoder;
import com.vysotski.funpay.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public User login(String login, String password) throws ServiceException {
        String encodedPassword = new String(PasswordEncoder.encodePassword(password));
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDao userDaoImpl = daoFactory.getUserDao();
        User currentUser = new User();
        try {
            currentUser = userDaoImpl.findByLoginAndPassword(login, encodedPassword);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return currentUser;
    }

    public List<User> registration(String login, String password, String email) throws ServiceException {
        if (!UserValidator.validateUserData(login, password, email)) {
            throw new ServiceException("Incorrect registration data");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        List<User> users = new ArrayList<>();
        String encodedPassword = new String(PasswordEncoder.encodePassword(password));
        try {
            if (!userDao.findByLogin(login)) {
                User user = new User();
                user.setLogin(login);
                user.setPassword(encodedPassword);
                user.setEmail(email);
                userDao.create(user);
                users.add(user);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }


    public String logOut() {
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }



    public String goToRegistrationPage() {
        String page = ConfigurationManager.getProperty("path.page.registration-page");
        return page;
    }


    public String goToMainPage() {

        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }

    public void blockUserById(long blockId){
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDao userDao = daoFactory.getUserDao();
        try {
            userDao.blockUserById(blockId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public List<User> selectAll() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDao userDaoImpl = daoFactory.getUserDao();
        List<User> users = new ArrayList<>();
        try {
            users = userDaoImpl.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}
