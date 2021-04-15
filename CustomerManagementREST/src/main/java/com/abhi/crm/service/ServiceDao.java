package com.abhi.crm.service;

import java.util.List;

import com.abhi.crm.entity.Customer;

public interface ServiceDao {
	
	public List<Customer> getCustomerList();
	
	public Customer getCustomer(int id);
	
	public void saveCustomer(Customer customer);
	
	public void deleteCustomer(int id);
}
