package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {

    private static final String PARAM_STATUS = "status";
    private static final String PARAM_USER_ID = "userID";
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String userStatus = request.getParameter(PARAM_STATUS);
        long userId = Long.parseLong(request.getParameter(PARAM_USER_ID));
//        TODO smth went wrong( cherez DAO nado find user, potom userservice?
//        List<User> users = userService.findById(userId); //zdes' DAO
        User user = userService.blockUserById(userId);
        request.setAttribute("status", userStatus);
        String page = ConfigurationManager.getProperty("path.page.admin-page");
        return page;
    }
}
