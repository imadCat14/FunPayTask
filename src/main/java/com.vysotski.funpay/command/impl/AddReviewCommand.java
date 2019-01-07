package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.entity.Server;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddReviewCommand implements Command {
    Logger logger = LogManager.getLogger();
    private static final String PARAM_REVIEW = "reviewText";
    private static final String PARAM_SERVER_NAME = "serverName";
    private static final String PARAM_SERVER_ID = "serverId";
    private static final String PARAM_USER_LOGIN = "login";
    ServerService serverService = new ServerService();
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Server server = new Server();
        List<Review> reviews = new ArrayList<>();
        try {
            String textReview = request.getParameter(PARAM_REVIEW);
            long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
            String login = request.getParameter(PARAM_USER_LOGIN);
            String serverName = request.getParameter(PARAM_SERVER_NAME);
            server = serverService.findServerByName(serverName).get(0);
            long userId = userService.takeUserId(login);
            Review review = serverService.addReview(textReview, serverId, userId);
            reviews.add(review);
            reviews = serverService.findReviews(serverId);
            request.setAttribute("server", server);
            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
