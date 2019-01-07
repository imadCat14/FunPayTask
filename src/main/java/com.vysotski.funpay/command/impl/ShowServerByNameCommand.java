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
import java.util.ArrayList;
import java.util.List;

import static com.vysotski.funpay.dao.AbstractDao.logger;

public class ShowServerByNameCommand implements Command {

    private static ServerService serverService = new ServerService();
    private static final String PARAM_SERVER_NAME = "serverName";
    String page=null;
    @Override
    public String execute(HttpServletRequest request) {
        String serverName = request.getParameter(PARAM_SERVER_NAME).trim();
        List<Server> servers=new ArrayList<>();
        HttpSession session = request.getSession();
        RoleType userType= (RoleType) session.getAttribute("userRole");
        try{
            servers= serverService.findServersByNameFragment(serverName);
            request.setAttribute("servers", servers);
            page = userType==RoleType.ADMIN? ConfigurationManager.getProperty("path.page.admin-page"):ConfigurationManager.getProperty("path.page.main-page");
        }
        catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
