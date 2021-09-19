package com.web.store.service;

import java.util.List;

import com.web.store.model._06_order.OrdBean;

public interface ContactUsOrdService {
	
	List<OrdBean> findOrdBeanById(Integer ordId);
}

