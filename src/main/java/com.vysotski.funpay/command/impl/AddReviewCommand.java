package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddReviewCommand implements Command {
    private static final String PARAM_REVIEW = "textReview";
    private static final String PARAM_SERVER_ID = "serverId";
    ServerService serverService = new ServerService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String textReview = request.getParameter(PARAM_REVIEW);
        long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
        long userID=Long.parseLong(request.getParameter("userID"));
        List<Review> reviews = serverService.findReviews(serverId);
        Review review = serverService.addReview(textReview,serverId,1);
        // Server server=userService.defineServer(serverId);
        request.setAttribute("userID", userID);
        request.setAttribute("serverId", serverId);
        request.setAttribute("reviews", reviews);
        String page = ConfigurationManager.getProperty("path.page.reviews-page");
        return page;

    }
}
