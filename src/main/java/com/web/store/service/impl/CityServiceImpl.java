package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._05_customer.CitySelectBean;
import com.web.store.repository.CityRepository;
import com.web.store.service.CityService;

@Transactional
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Override
	public List<CitySelectBean> getAllCitys(String groupCity) {
		return cityRepository.getAllCitys(groupCity);
	}

	@Override
	public CitySelectBean getCity(Integer id) {
		return cityRepository.getCity(id);
	}

}