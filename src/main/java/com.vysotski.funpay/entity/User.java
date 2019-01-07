package com.vysotski.funpay.entity;

public class User extends Entity {
    private long userId;
    private String login;
    private String password;
    private String email;
    private RoleType userRole;
    private StatusEnum userStatus;

    public User(String login, String password, String email, RoleType userRole, StatusEnum userStatus) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }
    public User(String login, String password,String email, RoleType userRole) {
        this.login = login;
        this.password = password;
        this.email=email;
        this.userRole = userRole;

    }
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(long userId, String login) {
        this.userId = userId;
        this.login = login;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((userStatus == null) ? 0 : userStatus.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (userId != other.userId)
            return false;
        if (userRole != other.userRole)
            return false;
        if (userStatus != other.userStatus)
            return false;
        return true;
    }
}
