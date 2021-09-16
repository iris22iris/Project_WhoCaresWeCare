package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._02_customerService.DmBean;
import com.web.store.repository.DmDao;
import com.web.store.service.DmService;

@Transactional
@Service
public class DmServiceImpl implements DmService {

	DmDao dmDao;
	
	@Autowired
	public DmServiceImpl(DmDao dmDao) {
		this.dmDao = dmDao;
	}

	@Override
	public List<DmBean> getAllDms() {
		return dmDao.getAllDms();
	}

	@Override
	public List<DmBean> getDmid(Integer dmId) {
		return dmDao.getDmid(dmId);
	}

}
