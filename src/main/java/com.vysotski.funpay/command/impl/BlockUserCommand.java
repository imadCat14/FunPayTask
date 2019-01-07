package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BlockUserCommand implements Command {

    private static final String PARAM_STATUS = "status";
    private static final String PARAM_USER_ID = "userID";
    UserService userService = new UserService();
    private String page;

    @Override
    public String execute(HttpServletRequest request) {
        String userStatus = request.getParameter(PARAM_STATUS);
        long userId = Long.parseLong(request.getParameter(PARAM_USER_ID));
        try{
            userService.blockUserById(userId);
            List<User> users=userService.selectAll();
            request.setAttribute("users",users);
        }catch (ServiceException e){
//            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty("path.page.users-page");
        return page;
    }
}
