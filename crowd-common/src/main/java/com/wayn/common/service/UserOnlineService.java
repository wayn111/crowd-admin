package com.wayn.common.service;

import com.wayn.common.domain.User;
import com.wayn.common.domain.UserOnline;

import java.util.List;

public interface UserOnlineService {

    List<UserOnline> list();

    List<User> listUser();

    void forceLogout(String sessionId);

    String getUserName(String sessionId);
}
