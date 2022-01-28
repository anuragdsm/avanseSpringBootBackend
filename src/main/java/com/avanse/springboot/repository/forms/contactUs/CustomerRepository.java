package com.avanse.springboot.repository.forms.contactUs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.forms.contactUs.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
