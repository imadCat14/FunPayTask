package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class FillNewServerData implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.new-server-form-page");
        return page;
    }
}
