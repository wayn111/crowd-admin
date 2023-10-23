package com.wayn.common.service;

import com.wayn.common.domain.User;
import com.wayn.common.domain.UserOnline;
import com.wayn.common.domain.dto.WsUserPrincipal;
import org.apache.shiro.session.Session;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserOnlineService {

    void put(String key, Object value);

    void remove(String key);

    List<UserOnline> list();

    List<User> listUser();

    void forceLogout(String sessionId);

    String getUserName(String sessionId);

    boolean checkUserLogin(String userId);

    Set<WsUserPrincipal> wsUserList();
}
