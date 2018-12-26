package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {
    private UserService userService = new UserService();
    public String execute(HttpServletRequest request) {
        return userService.goToRegistrationPage();
    }
}
