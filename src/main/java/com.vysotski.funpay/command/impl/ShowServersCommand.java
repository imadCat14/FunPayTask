package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowServersCommand implements Command {
    private static ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            List<Server> servers = serverService.selectAll();
            request.setAttribute("servers", servers);

        }catch (ServiceException e){
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty("path.page.admin-page");
        return page;
    }
}
