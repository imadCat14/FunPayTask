package com.vysotski.funpay.command;


import com.vysotski.funpay.command.impl.AddReviewCommand;
import com.vysotski.funpay.command.impl.AddServerCommand;
import com.vysotski.funpay.command.impl.BlockUserCommand;
import com.vysotski.funpay.command.impl.ChangeLanguageCommand;
import com.vysotski.funpay.command.impl.ChangeUserStatusCommand;
import com.vysotski.funpay.command.impl.ErrorCommand;
import com.vysotski.funpay.command.impl.GoToRegistrationPageCommand;
import com.vysotski.funpay.command.impl.LogInCommand;
import com.vysotski.funpay.command.impl.LogOutCommand;
import com.vysotski.funpay.command.impl.RegistrationCommand;
import com.vysotski.funpay.command.impl.ShowReviewsCommand;
import com.vysotski.funpay.command.impl.ShowServersCommand;
import com.vysotski.funpay.command.impl.ShowUsersCommand;

import java.util.EnumMap;

public class CommandMap {
    private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class){
        {
            this.put(CommandType.LOGIN, new LogInCommand());
            this.put(CommandType.LOGOUT, new LogOutCommand());
            this.put(CommandType.GOTOREGISTRATIONPAGE, new GoToRegistrationPageCommand());
            this.put(CommandType.GO_TO_MAIN_PAGE, new ErrorCommand());
            this.put(CommandType.REGISTRATION, new RegistrationCommand());
            this.put(CommandType.SEE_REVIEWS, new ShowReviewsCommand());
            this.put(CommandType.ADD_REVIEW, new AddReviewCommand());
            this.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
//
            this.put(CommandType.ADD_SERVER, new AddServerCommand());
            this.put(CommandType.CHANGE_STATUS, new ChangeUserStatusCommand());
            this.put(CommandType.BLOCK_USER, new BlockUserCommand());
            this.put(CommandType.SHOW_USERS, new ShowUsersCommand());
            this.put(CommandType.SHOW_SERVERS, new ShowServersCommand());
//            this.put(CommandType.MARK_SERVER, new MarkServerCommand());
        }
    };

    private static CommandMap instance = new CommandMap();

    private CommandMap(){}

    public static CommandMap getInstance(){ return instance;}

    public Command get(CommandType key){ return commandMap.get(key);}

}
