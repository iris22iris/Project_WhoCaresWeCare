package com.web.store.service;

import java.util.List;

import com.web.store.model._02_customerService.DmBean;

public interface DmService {

	List<DmBean> getAllDms();
	
	List<DmBean> getDmid(Integer dmId);
}
