package com.vysotski.funpay.entity;

import java.util.Objects;

public class User extends Entity {
    private long userId;
    private String login;
    private String password;
    private String email;
    private RoleType userRole;
    private StatusEnum userStatus;

    public User(String login, String password, String email,RoleType userRole, StatusEnum userStatus) {
        this.login = login;
        this.password = password;
        this.email=email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }
    public User(String login, String password,String email, RoleType userRole) {
        this.login = login;
        this.password = password;
        this.email=email;
        this.userRole = userRole;

    }
    public User(String login, String password,String email) {
        this.login = login;
        this.password = password;
        this.email=email;


    }

    public User() {

    }

    public User(long userId, String login, String password, String email, RoleType userRole, StatusEnum userStatus) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleType userRole) {
        this.userRole = userRole;
    }

    public StatusEnum getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(StatusEnum userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", userStatus=" + userStatus +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, userRole, userStatus, email, login, password);
    }
}
