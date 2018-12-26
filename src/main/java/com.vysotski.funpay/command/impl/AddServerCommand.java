package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.resource.MessageManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddServerCommand implements Command {
    private static final String PARAM_NAME_SERVER = "serverName";
    private static final String PARAM_NAME_DESCRIPTION = "serverDescription";
    private static final String PARAM_NAME_CHRONICLE_ID = "chronicleId";
    private String page;
    private String message;
    private ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String serverName = request.getParameter(PARAM_NAME_SERVER);
//       TODO
        long chronicleId = Long.parseLong(request.getParameter(PARAM_NAME_CHRONICLE_ID));
        String serverDescription = request.getParameter(PARAM_NAME_DESCRIPTION);
        List<Server> servers = serverService.addServer(serverName,serverDescription, chronicleId);
        if (!servers.isEmpty()) {
            request.setAttribute("server", servers);
            message = MessageManager.getProperty("message.addserver");
            request.setAttribute("wrongInfoData", message);
//           izmenit' stranicu
//            TODO stranica!!! funkciya tol'ko dlya admina(na admin page dobavit')
            page = ConfigurationManager.getProperty("path.page.servers-page");
//
        } else {
            message = MessageManager.getProperty("message.addservererror");
            request.setAttribute("wrongInfoData", message);
//            stranica!!!
            page = ConfigurationManager.getProperty("path.page.servers-page");
//
        }
        return page;
    }
}
