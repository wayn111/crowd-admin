package com.wayn.commom.service;

import com.wayn.commom.domain.User;
import com.wayn.commom.domain.UserOnline;

import java.util.List;

public interface UserOnlineService {

    List<UserOnline> list();

    List<User> listUser();

    void forceLogout(String sessionId);

    String getUserName(String sessionId);
}
