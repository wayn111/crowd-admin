package com.wayn.service;

import java.util.List;

import com.wayn.domain.UserOnline;

public interface UserOnlineService {

	
	List<UserOnline> list();
	
	void forceLogout(String sessionId);
}
