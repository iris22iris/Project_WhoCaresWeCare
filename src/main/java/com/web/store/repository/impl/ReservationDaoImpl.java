package com.web.store.repository.impl;

import java.util.List;

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
	
//	使用顧客編號查詢預約紀錄
	@Override
	public List<ReservationBean> findReservationBeanByCustId(Integer custId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ReservationBean rb WHERE rb.customerBean.custId = :cid ";
		if (session.createQuery(hql, ReservationBean.class).setParameter("cid", custId).getResultList().size() > 0) {
			return session.createQuery(hql, ReservationBean.class)
						  .setParameter("cid", custId)
						  .getResultList();
		} else {
			return null;
		}
	}
	
//	使用預約編號查詢預約紀錄
	@Override
	public ReservationBean findReservationBeanById(Integer custId, Integer reservationId) {
		Session session = factory.getCurrentSession();
		String hql = " FROM ReservationBean rb WHERE rb.reservationId = :rid AND rb.customerBean.custId = :cid ";
		if (session.createQuery(hql, ReservationBean.class).setParameter("rid", reservationId)
				   .setParameter("cid", custId).getResultList().size() > 0) {
			return session.createQuery(hql, ReservationBean.class)
						  .setParameter("rid", reservationId)
						  .setParameter("cid", custId)
						  .getSingleResult();
		} else {
			return null;
		}
	}
	
}