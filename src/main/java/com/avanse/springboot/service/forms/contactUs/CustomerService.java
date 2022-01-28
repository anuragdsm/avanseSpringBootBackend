package com.avanse.springboot.service.forms.contactUs;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.contactUs.Customer;
import com.avanse.springboot.repository.forms.contactUs.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	@Transactional
	public void addCustomer(Customer customer) {
		customerRepository.save(customer); 
	}
	
	public Optional<Customer> getCustomerById(long id){
		return customerRepository.findById(id);
	}
	
	@Transactional
	public void deleteCustomer(long id) {
		customerRepository.deleteById(id);
	}
	
	public long numberOfCustomers() {
		return customerRepository.count();
	}
	

}
