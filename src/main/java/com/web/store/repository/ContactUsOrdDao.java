package com.web.store.repository;

import java.util.List;

import com.web.store.model._06_order.OrdBean;

public interface ContactUsOrdDao {

	List<OrdBean> findOrdBeanById(Integer ordId);

}
