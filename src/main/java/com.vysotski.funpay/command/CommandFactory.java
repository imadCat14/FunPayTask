package com.vysotski.funpay.command;

import com.vysotski.funpay.command.impl.EmptyCommand;
import com.vysotski.funpay.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    Logger logger = LogManager.getLogger();
    public Command defineCommand(HttpServletRequest request) {
        Command currentCommand = new EmptyCommand();
        String commandName = request.getParameter("command").toUpperCase().replaceAll("-", "_");
        if (commandName == null || commandName.isEmpty()) {
            return currentCommand;
        }
        try {
            currentCommand = CommandMap.getInstance().get(CommandType.valueOf(commandName));
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute("wrongAction", commandName + MessageManager.getProperty("message.wrongaction"));

        }
        return currentCommand;
    }
}
