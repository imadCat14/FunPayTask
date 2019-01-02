package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
import com.vysotski.funpay.entity.User;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServiceException;
import com.vysotski.funpay.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsersCommand implements Command {
    Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            List<User> users = userService.selectAll();
            request.setAttribute("users", users);
            page = ConfigurationManager.getProperty("path.page.users-page");
        } catch (ServiceException e) {
            throw new CommandException();
        }
        return page;
    }
}
