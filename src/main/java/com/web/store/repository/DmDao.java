package com.web.store.repository;

import java.util.List;

import com.web.store.model._02_customerService.DmBean;

public interface DmDao {

	List<DmBean> getAllDms();

	List<DmBean> getDmid(Integer dmId);

}
