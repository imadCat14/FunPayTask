package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.resource.MessageManager;
import com.vysotski.funpay.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {
    private UserService userService = new UserService();
    public String execute(HttpServletRequest request) {
        String message = MessageManager.getProperty("message.wrongcommand");
        request.setAttribute("wrongInfoData", message);
        return userService.goToMainPage();
    }
}
