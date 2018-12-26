package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Mark;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MarkServerCommand implements Command {
    private static final String PARAM_MARK = "mark";
    private static final String PARAM_SERVER_ID = "serverId";
    private static final String PARAM_USER_ID = "userId";

    ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String serverMark = request.getParameter(PARAM_MARK);
        long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
        long userID=Long.parseLong(request.getParameter(PARAM_USER_ID));
        List<Mark> marks = serverService.findMarks(serverId);
        Mark mark = serverService.addMarkServer(userID,serverId,1);
        // Server server=userService.defineServer(serverId);
        request.setAttribute("userID", userID);
        request.setAttribute("serverId", serverId);
        request.setAttribute("mark", marks);
//        TODO dobavit' na reviews page
        String page = ConfigurationManager.getProperty("path.page.reviews-page");
//        !!!
        return page;
    }
}
