package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.RoleType;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.vysotski.funpay.dao.AbstractDao.logger;


public class ShowServersCommand implements Command {
    private static ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        RoleType userType= (RoleType) session.getAttribute("userRole");
        try {
            List<Server> servers = serverService.selectAll();
            request.setAttribute("servers", servers);
            page = userType==RoleType.ADMIN?ConfigurationManager.getProperty("path.page.admin-page"):ConfigurationManager.getProperty("path.page.main-page");
            // page = ConfigurationManager.getProperty("path.page.admin-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
