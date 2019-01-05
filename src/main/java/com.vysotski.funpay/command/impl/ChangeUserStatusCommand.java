package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeUserStatusCommand implements Command {
    private static final String PARAM_SELECTED_STATUS = "selectedStatus";
    private static final String PARAM_SELECTED_USER = "selectedUser";
    private UserService userService = new UserService();
    private String page;
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String selectedStatus=request.getParameter(PARAM_SELECTED_STATUS);
        String selectedUser=request.getParameter(PARAM_SELECTED_USER);
        try{
            userService.changeStatus(selectedStatus,selectedUser);
            List<User> users=userService.selectAll();
            request.setAttribute("users",users);
        }catch (ServiceException e){
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty("path.page.users-page");
        return page;
    }
}
