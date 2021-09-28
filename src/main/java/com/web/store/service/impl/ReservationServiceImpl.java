package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._03_rent.ReservationBean;
import com.web.store.repository.ReservationDao;
import com.web.store.service.ReservationService;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationDao reservationDao;
	
	@Autowired
	public ReservationServiceImpl(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
//	使用顧客編號查詢預約紀錄
	@Override
	public List<ReservationBean> findReservationBeanByCustId(Integer custId) {
		return reservationDao.findReservationBeanByCustId(custId);
	}
	
//	使用預約編號查詢預約紀錄
	@Override
	public ReservationBean findReservationBeanById(Integer custId, Integer reservationId) {
		return reservationDao.findReservationBeanById(custId, reservationId);
	}

}