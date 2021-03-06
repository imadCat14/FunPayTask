package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.vysotski.funpay.dao.AbstractDao.logger;

public class ShowReviewsCommand implements Command {
    private static final String PARAM_SERVER_ID = "serverId";
    private static final String PARAM_SERVER_NAME = "serverName";
    private ServerService serverService = new ServerService();
    String page;

    @Override
    public String execute(HttpServletRequest request) {
        long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
        String serverName = request.getParameter(PARAM_SERVER_NAME);
        Server server = new Server();
        try {
            server = serverService.findServerByName(serverName).get(0);
            List<Review> reviews = serverService.findReviews(serverId);
            request.setAttribute("server", server);
            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
