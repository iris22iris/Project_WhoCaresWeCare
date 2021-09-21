package com.web.store.repository;

import com.web.store.model._03_rent.ReservationBean;

public interface ReservationDao {

	//	使用預約編號查詢預約紀錄
	ReservationBean findReservationBeanById(Integer reservationId);

}