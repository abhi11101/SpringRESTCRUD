package com.abhi.crm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.crm.entity.Customer;
import com.abhi.crm.exceptionhandling.CustomerException;
import com.abhi.crm.service.ServiceDao;

@RestController
@RequestMapping("/api")
public class ControllerREST {
	
	@Autowired
	private ServiceDao servicedao;
	
	public String geturl()
	{
		
		String result = "";
		return result;
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomerList(){
		
		return servicedao.getCustomerList();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = servicedao.getCustomer(customerId);
		if(customer ==null) {
			throw new CustomerException("Customer Not Found- " + customerId);
		}
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		
		customer.setId(0);
		servicedao.saveCustomer(customer);
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		servicedao.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		Customer customer = servicedao.getCustomer(customerId);
		if(customer==null) {
			throw new CustomerException("Customer Not found--->" +customerId);
		}
		return "Deleted customer- " + customerId;
	}
}





//http://localhost:8080/CustomerManagementREST/api?&TransId=787789573538&uid=777&RequestType=1&AppId=14&String=checkallcountries

//header --
//aus --41

