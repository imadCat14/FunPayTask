package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.Review;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServerService;
import com.vysotski.funpay.service.ServiceException;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.vysotski.funpay.dao.AbstractDao.logger;

public class ViewReviewsCommand implements Command {

    private static final String PARAM_USER_ID = "userID";

    private ServerService serverService = new ServerService();
    String page;

    @Override
    public String execute(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter(PARAM_USER_ID));

        try {
            List<Review> reviews = serverService.findUserReviews(userId);


            request.setAttribute("reviews", reviews);
            page = ConfigurationManager.getProperty("path.page.reviews-page");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return "/jsp/admin/userReviews.jsp";
    }
}
