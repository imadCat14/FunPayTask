package com.vysotski.funpay.dao;

public class SQLQuery {
    //user
    public static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,email,roleID,status) VALUES(?,?,?,?,?)";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM user WHERE login =?";
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE userID =?";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_BLOCK_USER_BY_ID = "UPDATE * user SET status=0 WHERE userID=?";
    //server
    public static final String SQL_SELECT_ALL_SERVERS = "SELECT server.serverName, chronicle.chronicleId, chronicle.chronicleName," +
            "server.serverDescription,AVG(mark.mark), server.serverId  FROM server  LEFT JOIN mark " +
            "ON server.serverId=mark.serverId LEFT JOIN chronicle ON server.chronicleId=chronicle.chronicleId GROUP BY server.serverId";
    public static final String SQL_SELECT_SERVER_REVIEWS = "SELECT review.serverId, server.serverName, user.login," +
            " reviews.reviewText,review.reviewDate FROM review LEFT JOIN user on user.userID=review.userID" +
            " LEFT JOIN server on review.serverId=server.serverId where review.serverId=?";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO review(serverId, userID, reviewText,reviewDate) VALUES(?,?,?,?)";
    public static final String SQL_FIND_SERVER_BY_ID = "SELECT * FROM server WHERE serverId =?";
    public static final String SQL_FIND_SERVER_BY_NAME = "SELECT * FROM server WHERE serverName=?";
    public static final String SQL_DELETE_SERVER_BY_ID = "DELETE FROM server WHERE serverId =?";
    public static final String SQL_ADD_NEW_SERVER = "INSERT INTO server(serverId, chronicleId, severName, serverDescription) VALUES(?,?,?,?)";
    public static final String SQL_MARK_SERVER = "INSERT INTO mark(mark, userID, serverId) VALUES(?,?,?)";
    public static final String SQL_SELECT_SERVER_MARKS = "SELECT * FROM mark WHERE serverId=?";
    public static final String SQL_INSERT_CHRONICLE = "INSERT INTO chronicle(chronicleName) VALUES(?)";
    public static final String SQL_FIND_CHRONICLE_ID_BY_CHRONICLE_NAME = "SELECT chronicleId FROM chronicle WHERE chronicleName=?";
    public static final String SQL_UPDATE_USER_STATUS = "UPDATE user set status=? where login=?";
    public static final String SQL_FIND_SERVER_MARK_FROM_USER = "";
    public static final String SQL_TAKE_SERVER_INFORMATION_BY_NAME = "";
}

