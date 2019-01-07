package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.RoleType;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.entity.StatusEnum;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.resource.MessageManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import static com.vysotski.funpay.dao.AbstractDao.logger;

public class LogInCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private UserService userService = new UserService();
    private ServerService serverService = new ServerService();
    private String page;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        User currentUser = null;

        try {
            currentUser = userService.login(login, password);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (currentUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", currentUser);
            RoleType userRole=currentUser.getUserRole();
            session.setAttribute("userRole", userRole);
            List<Server> servers = null;
            try {
                servers = serverService.selectAll();
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
            request.setAttribute("servers", servers);
            definedPage(currentUser);
            String message = MessageManager.getProperty("message.blocked");
            request.setAttribute("wrongInfoData", message);
        } else {
            String message = MessageManager.getProperty("message.loginerror");
            request.setAttribute("wrongInfoData", message);
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return page;
    }

    private String definedPage(User currentUser) {
        if (currentUser.getUserRole() == RoleType.ADMIN) {
            page = ConfigurationManager.getProperty("path.page.admin-page");
        } else if (currentUser.getUserStatus() == StatusEnum.BLOCKED) {
            page = ConfigurationManager.getProperty("path.page.index");
        } else {
            page = ConfigurationManager.getProperty("path.page.main-page");
        }
        return page;
    }
}
