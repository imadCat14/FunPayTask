package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddReviewCommand implements Command {
    Logger logger = LogManager.getLogger();
    private static final String PARAM_REVIEW = "textReview";
    private static final String PARAM_SERVER_ID = "serverId";
    ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            String textReview = request.getParameter(PARAM_REVIEW);
            long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
            long userId = Long.parseLong(request.getParameter("userID"));
            List<Review> reviews = serverService.findReviews(serverId);
            Review review = serverService.addReview(textReview, serverId, 1);
            // Server server=serverService.defineServer(serverId);
            request.setAttribute("userID", userId);
            request.setAttribute("serverId", serverId);
            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        }catch (ServiceException e){
            logger.log(Level.ERROR, e);
            throw new CommandException();
        }
        return page;
    }
}
