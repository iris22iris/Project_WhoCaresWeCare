package com.web.store.dao;

import java.util.List;

import com.web.store.model._05_customer.CustomerBean;


public interface CustomerDao {
	CustomerBean getCustomerById(int id);
	
	List<CustomerBean> getCustomers();

	Object save(CustomerBean bean);
	
	void updateCustomer(CustomerBean bean); 

	void deleteCustomerByPrimaryKey(int key);
}
