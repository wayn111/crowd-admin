package com.wayn.commom.service;

import java.util.List;

import com.wayn.commom.domain.UserOnline;

public interface UserOnlineService {

	
	List<UserOnline> list();
	
	void forceLogout(String sessionId);
}
