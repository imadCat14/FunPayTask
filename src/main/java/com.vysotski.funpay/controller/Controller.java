package com.vysotski.funpay.controller;

import com.vysotski.funpay.command.Command;
import com.vysotski.funpay.command.CommandException;
import com.vysotski.funpay.command.CommandFactory;
import com.vysotski.funpay.pool.ConnectionPool;
import com.vysotski.funpay.resource.ConfigurationManager;
import com.vysotski.funpay.resource.MessageManager;
import com.vysotski.funpay.service.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {
        String page = null;
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            e.printStackTrace();
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
