package com.web.store.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model._05_customer.CustomerBean;
import com.web.store.repository.CustomerDao;
import com.web.store.service.CustomerService;


@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	CustomerDao custDao;
	
	@Autowired
	public void setCustDao(CustomerDao custDao) {
		this.custDao = custDao;
	}

	@Override
	public CustomerBean getCustomerById(int id) {
	
		return custDao.getCustomerById(id);
	}

	@Override
	public List<CustomerBean> getCustomers() {
		return custDao.getCustomers();
	}

	@Override
	public Object save(CustomerBean bean) {
		return custDao.save(bean);
	}

	@Override
	public void updateCustomer(CustomerBean bean) {
		custDao.updateCustomer(bean);
	}

	@Override
	public void deleteCustomerByPrimaryKey(int key) {
		custDao.deleteCustomerByPrimaryKey(key);
	}

	@Override
	public CustomerBean checkIDPassword(String custId, String password) {
		return custDao.checkIDPassword(custId, password);

	}

	@Override
	public boolean idExists(String id) {
		return custDao.idExists(id);
	}

	@Override
	public CustomerBean get(Integer id) {
		return custDao.get(id);
	}
}
