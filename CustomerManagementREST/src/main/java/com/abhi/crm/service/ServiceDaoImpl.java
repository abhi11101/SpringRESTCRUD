package com.abhi.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.crm.dao.CustomerDAO;
import com.abhi.crm.entity.Customer;

@Service
public class ServiceDaoImpl implements ServiceDao {

	@Autowired
	private CustomerDAO customerdao;
	
	@Override
	@Transactional
	public List<Customer> getCustomerList() {
		
		return customerdao.getCustomerList();
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		
		return customerdao.getCustomer(id);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerdao.saveCustomer(customer);

	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerdao.deleteCustomer(id);

	}

}
