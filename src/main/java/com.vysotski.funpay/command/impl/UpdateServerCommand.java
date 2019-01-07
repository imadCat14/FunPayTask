package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.vysotski.funpay.dao.AbstractDao.logger;

public class UpdateServerCommand implements Command {

    private static ServerService serverService = new ServerService();
    private static final String PARAM_SERVER_NAME = "serverName";
    private static final String PARAM_SERVER_DESCRIPTION = "newDescription";
    String page=null;

    @Override
    public String execute(HttpServletRequest request) {
        String serverName = request.getParameter(PARAM_SERVER_NAME).trim();
        String serverDescription=request.getParameter(PARAM_SERVER_DESCRIPTION).trim();
        List<Server> servers=new ArrayList<>();
        Server server=null;
        try{
            serverService.updateDescription(serverDescription,serverName);
            servers= serverService.selectAll();
            request.setAttribute("servers", servers);
            page = ConfigurationManager.getProperty("path.page.admin-page");
        }
        catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
