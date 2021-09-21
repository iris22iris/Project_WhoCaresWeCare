package com.web.store.service;

import com.web.store.model._03_rent.ReservationBean;

public interface ReservationService {

	//	使用預約編號查詢預約紀錄
	ReservationBean findReservationBeanById(Integer reservationId);

}