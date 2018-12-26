package com.vysotski.funpay.dao;

public class SQLQuerry {
    //user
    public static final String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
    public static final String SQL_INSERT_USER = "INSERT INTO user(login,password,mail,roleID,status) VALUES(?,?,?,?,1)";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM user WHERE login =?";
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE userID =?";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_BLOCK_USER_BY_ID = "UPDATE * user SET status=0 WHERE userID=?";
    //server
    public static final String SQL_SELECT_ALL_SERVERS = "SELECT server.*,chronicle.chronicleName  FROM server LEFT JOIN chronicle ON server.chronicleId=chronicle.chronicleId";
    public static final String SQL_SELECT_SERVER_REVIEWS = "SELECT * FROM review WHERE serverId=?";
    public static final String SQL_INSERT_REVIEW = "INSERT INTO review(serverId, userID, textReview,dateReview) VALUES(?,?,?,?)";
    public static final String SQL_FIND_SERVER_BY_ID = "SELECT * FROM server WHERE serverId =?";
    public static final String SQL_FIND_SERVER_BY_NAME = "SELECT * FROM server WHERE serverName=?";
    public static final String SQL_DELETE_SERVER_BY_ID = "DELETE FROM server WHERE serverId =?";
    public static final String SQL_ADD_NEW_SERVER = "INSERT INTO server(serverId, chronicleId, severName, serverDescription) VALUES(?,?,?,?)";
    public static final String SQL_MARK_SERVER = "INSERT INTO mark(mark, userID, serverId) VALUES(?,?,?)";
    public static final String SQL_SELECT_SERVER_MARKS = "SELECT * FROM mark WHERE serverId=?";
}
