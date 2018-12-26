package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReviewsCommand implements Command {
    private static final String PARAM_SERVER_ID = "serverId";
    private ServerService serverService = new ServerService();
    String page;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
        List<Review> reviews = serverService.findReviews(serverId);
        // Server server=userService.defineServer(serverId);
        request.setAttribute("server", serverId);
        request.setAttribute("reviews", reviews);
        //request.setAttribute("", reviews);
        page = ConfigurationManager.getProperty("path.page.reviews-page");
        return page;
    }
}
