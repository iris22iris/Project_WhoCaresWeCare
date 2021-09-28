package com.web.store.repository;

import java.util.List;

import com.web.store.model._03_rent.ReservationBean;

public interface ReservationDao {
	
	//	使用顧客編號查詢預約紀錄
	List<ReservationBean> findReservationBeanByCustId(Integer custId);

	//	使用預約編號查詢預約紀錄
	ReservationBean findReservationBeanById(Integer custId, Integer reservationId);

}