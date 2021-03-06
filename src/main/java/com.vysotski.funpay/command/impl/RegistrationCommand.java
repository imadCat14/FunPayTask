package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.resource.MessageManager;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.vysotski.funpay.dao.AbstractDao.logger;

public class RegistrationCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private String page;
    private String message;
    private UserService userService = new UserService();


    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        List<User> users = null;
        try {
            users = userService.registration(login, password, email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (!users.isEmpty()) {
            request.setAttribute("user", users);
            message = MessageManager.getProperty("message.registration");
            request.setAttribute("wrongInfoData", message);
            page = ConfigurationManager.getProperty("path.page.registration-page");
        } else {
            message = MessageManager.getProperty("message.registrationerror");
            request.setAttribute("wrongInfoData", message);
            page = ConfigurationManager.getProperty("path.page.registration-page");
        }
        return page;
    }
}
