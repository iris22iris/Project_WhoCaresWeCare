package com.web.store.service;

import java.util.List;
import com.web.store.model._05_customer.CitySelectBean;

public interface CityService {
	List<CitySelectBean> getAllCitys(String groupCity);
	CitySelectBean getCity(Integer id);
}

