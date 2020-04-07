package com.wayn.commom.domain.vo;

import java.util.StringJoiner;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", UserResetPasswordVO.class.getSimpleName() + "[", "]")
                .add("oldPassword='" + oldPassword + "'")
                .add("newPassword='" + newPassword + "'")
                .toString();
    }
}
