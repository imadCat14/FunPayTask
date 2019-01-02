package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
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
    public String execute(HttpServletRequest request) throws CommandException {
        long serverId = Long.parseLong(request.getParameter(PARAM_SERVER_ID));
        try {
            List<Review> reviews = serverService.findReviews(serverId);
            request.setAttribute("server", serverId);
            request.setAttribute("reviews", reviews);
            //request.setAttribute("", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        }catch (ServiceException e) {
            throw new CommandException();
        }
        return page;
    }
}
