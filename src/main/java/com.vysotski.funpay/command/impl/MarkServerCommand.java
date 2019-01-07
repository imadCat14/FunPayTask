package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import static com.vysotski.funpay.dao.AbstractDao.logger;

public class MarkServerCommand implements Command {
    private static final String PARAM_MARK = "mark";
    private static final String PARAM_SERVER_ID = "serverId";
    private static final String PARAM_USER_LOGIN = "login";

    private UserService userService = new UserService();
    private ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) {
        int mark = Integer.parseInt(request.getParameter(PARAM_MARK));
        long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
        String login = request.getParameter(PARAM_USER_LOGIN);
        Server server=new Server();
        List<Server> servers = new ArrayList<>();
        try {
            List<Review> reviews = serverService.findReviews(serverId);
            request.setAttribute("reviews", reviews);
            long userId = userService.takeUserId(login);
            serverService.markServer(userId, serverId, mark);
            server = serverService.findServerByName(request.getParameter("serverName")).get(0);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute("server", server);
        String page = ConfigurationManager.getProperty("path.page.reviews-page");
        return page;
    }
}
