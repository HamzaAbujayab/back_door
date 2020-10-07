package com.example.backdoor;

public class User {
    public String idUser;
    public String userNameUser;
    public String emailUser;
    public String passwordUser;

    public User(String idUser, String userNameUser, String emailUser, String passwordUser) {
        this.idUser = idUser;
        this.userNameUser = userNameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserNameUser() {
        return userNameUser;
    }

    public void setUserNameUser(String userNameUser) {
        this.userNameUser = userNameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setGmailUser(String gmailUser) {
        this.emailUser = gmailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
