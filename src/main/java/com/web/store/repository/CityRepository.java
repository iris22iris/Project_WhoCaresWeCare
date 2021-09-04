package com.web.store.repository;

import java.util.List;

import com.web.store.model._05_customer.CitySelectBean;


public interface CityRepository {
	List<CitySelectBean> getAllCitys(String groupCity);
	CitySelectBean getCity(Integer id);
}
