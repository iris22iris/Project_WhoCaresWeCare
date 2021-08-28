package com.web.store.repository;

import java.util.List;
import com.web.store.model._05_customer.CustomerBean;


public interface CustomerDao {
	CustomerBean getCustomerById(int id);
	List<CustomerBean> getCustomers();
	Object save(CustomerBean bean);
	void updateCustomer(CustomerBean bean); 
	void deleteCustomerByPrimaryKey(int key);
	public CustomerBean checkIDPassword(String account,String password);
	boolean idExists(String id);	
	CustomerBean get(Integer id);
}
