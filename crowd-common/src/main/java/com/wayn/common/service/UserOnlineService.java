package com.wayn.common.service;

import com.wayn.common.domain.User;
import com.wayn.common.domain.UserOnline;

import java.util.List;
import java.util.Map;

public interface UserOnlineService {

    void put(String key, Object value);

    void remove(String key);

    Map<String, Object> getActiveMap();

    List<UserOnline> list();

    List<User> listUser();

    void forceLogout(String sessionId);

    String getUserName(String sessionId);

    boolean checkUserLogin(String userId);
}
