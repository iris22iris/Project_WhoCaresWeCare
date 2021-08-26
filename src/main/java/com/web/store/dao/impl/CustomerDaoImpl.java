package com.web.store.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.dao.CustomerDao;
import com.web.store.model._05_customer.CustomerBean;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public CustomerDaoImpl() {
	}

	@Override
	public CustomerBean getCustomerById(int id) {
		CustomerBean bean = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean cb WHERE cb.custId = :id";
		try {
			bean = (CustomerBean) session.createQuery(hql).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			; // 表示查無紀錄
		}

		return bean;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerBean> getCustomers() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerBean";
		List<CustomerBean> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public Object save(CustomerBean bean) {
		Session session = factory.getCurrentSession();
		return session.save(bean);
	}

	@Override
	public void updateCustomer(CustomerBean bean) {
		Session session = factory.getCurrentSession();
		session.update(bean);

	}

	@Override
	public void deleteCustomerByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		CustomerBean customer = new CustomerBean();
		customer.setCustId(key);
		session.delete(customer);
	}

	@Override
	public CustomerBean checkIDPassword(String custId, String password) {
		CustomerBean cp = null;
		String hql = "FROM CustomerBean WHERE account = :account and password = :password";
		Session session = factory.getCurrentSession();
		List<CustomerBean> list = session.createQuery(hql, CustomerBean.class).setParameter("account", custId)
				.setParameter("password", password).getResultList();
		cp = (list.isEmpty() ? null : list.get(0));

		return cp;
	}

	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String hql = "From CustomerBean Where account = :account";
		Session session = factory.getCurrentSession();
		List<CustomerBean> list = session.createQuery(hql, CustomerBean.class).setParameter("account", id)
				.getResultList();
		exist = (list.isEmpty()) ? false : true;
		return exist;
	}
	
	@Override
	public CustomerBean get(Integer id) {
		return factory.getCurrentSession().get(CustomerBean.class, id);
	}
}
