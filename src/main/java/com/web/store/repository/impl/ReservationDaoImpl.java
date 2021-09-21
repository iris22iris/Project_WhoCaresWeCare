package com.web.store.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.model._03_rent.ReservationBean;
import com.web.store.repository.ReservationDao;

@Repository
public class ReservationDaoImpl implements ReservationDao {
	
	SessionFactory factory;

	@Autowired
	public ReservationDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
//	使用預約編號查詢預約紀錄
	@Override
	public ReservationBean findReservationBeanById(Integer reservationId) {
		Session session = factory.getCurrentSession();
		return session.get(ReservationBean.class, reservationId);
	}
	
}