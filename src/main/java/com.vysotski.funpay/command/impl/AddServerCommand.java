package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.resource.MessageManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.vysotski.funpay.dao.AbstractDao.logger;

public class AddServerCommand implements Command {
    private static final String PARAM_NAME_SERVER = "serverName";
    private static final String PARAM_NAME_DESCRIPTION = "serverDescription";
    private static final String PARAM_NAME_CHRONICLE = "serverChronicle";
    private ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) {
        String serverName = request.getParameter(PARAM_NAME_SERVER);
        String serverChronicle = request.getParameter(PARAM_NAME_CHRONICLE);
        String serverDescription = request.getParameter(PARAM_NAME_DESCRIPTION).replaceAll("<","");
        String page = null;
        String message;
        try {
            List<Server> servers = serverService.createServer(serverName, serverChronicle, serverDescription);
        if (servers.isEmpty()) {
        message = MessageManager.getProperty("message.notAddServer");
        request.setAttribute("infoData", message);
        page = ConfigurationManager.getProperty("path.page.new-server-form-page");
    } else {
        servers = serverService.selectAll();
        message = MessageManager.getProperty("message.addServer");
        request.setAttribute("infoData", message);
        request.setAttribute("servers", servers);
        page = ConfigurationManager.getProperty("path.page.new-server-form-page");
    }
} catch (ServiceException e) {
        logger.log(Level.ERROR, e);
        }
        return page;
    }
}
