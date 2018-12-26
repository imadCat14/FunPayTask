package com.vysotski.funpay.command.impl;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "locale";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String locale = request.getParameter(LANGUAGE_PARAM);
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE_ATTR, locale);
        page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        return page;
    }
}
