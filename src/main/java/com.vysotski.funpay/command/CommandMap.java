package com.vysotski.funpay.command;


import com.vysotski.funpay.command.impl.AddReviewCommand;
import com.vysotski.funpay.command.impl.AddServerCommand;
import com.vysotski.funpay.command.impl.BlockUserCommand;
import com.vysotski.funpay.command.impl.ChangeLanguageCommand;
import com.vysotski.funpay.command.impl.ChangeUserStatusCommand;
import com.vysotski.funpay.command.impl.ErrorCommand;
import com.vysotski.funpay.command.impl.FillNewServerData;
import com.vysotski.funpay.command.impl.GoToRegistrationPageCommand;
import com.vysotski.funpay.command.impl.LogInCommand;
import com.vysotski.funpay.command.impl.LogOutCommand;
import com.vysotski.funpay.command.impl.MarkServerCommand;
import com.vysotski.funpay.command.impl.RegistrationCommand;
import com.vysotski.funpay.command.impl.ShowServerByNameCommand;
import com.vysotski.funpay.command.impl.ShowServersCommand;
import com.vysotski.funpay.command.impl.ShowUsersCommand;
import com.vysotski.funpay.command.impl.UpdateServerCommand;
import com.vysotski.funpay.command.impl.ViewReviewsCommand;

import java.util.EnumMap;

public class CommandMap {
    private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class){
        {
            this.put(CommandType.LOGIN, new LogInCommand());
            this.put(CommandType.LOGOUT, new LogOutCommand());
            this.put(CommandType.GOTOREGISTRATIONPAGE, new GoToRegistrationPageCommand());
            this.put(CommandType.GO_TO_MAIN_PAGE, new ErrorCommand());
            this.put(CommandType.REGISTRATION, new RegistrationCommand());
            this.put(CommandType.ADD_REVIEW, new AddReviewCommand());
            this.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
//
            this.put(CommandType.ADD_SERVER, new AddServerCommand());
            this.put(CommandType.CHANGE_STATUS, new ChangeUserStatusCommand());
            this.put(CommandType.BLOCK_USER, new BlockUserCommand());
            this.put(CommandType.SHOW_USERS, new ShowUsersCommand());
            this.put(CommandType.SHOW_SERVERS, new ShowServersCommand());
            this.put(CommandType.FILL_NEW_SERVER_DATA, new FillNewServerData());
            this.put(CommandType.MARK_SERVER, new MarkServerCommand());
            this.put(CommandType.SHOW_SERVER_BY_NAME, new ShowServerByNameCommand());
            this.put(CommandType.UPDATE_SERVER, new UpdateServerCommand());
            this.put(CommandType.SEE_REVIEWS, new ViewReviewsCommand());
        }
    };

    private static CommandMap instance = new CommandMap();

    private CommandMap(){}

    public static CommandMap getInstance(){ return instance;}

    public Command get(CommandType key){ return commandMap.get(key);}

}
