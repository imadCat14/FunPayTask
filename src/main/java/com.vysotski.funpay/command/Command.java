package com.vysotski.funpay.command;

import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws ServiceException, CommandException;
}
