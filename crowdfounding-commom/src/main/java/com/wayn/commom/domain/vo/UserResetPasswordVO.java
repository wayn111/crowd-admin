package com.wayn.commom.domain.vo;

public class UserResetPasswordVO{

    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public UserResetPasswordVO setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserResetPasswordVO setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

}
