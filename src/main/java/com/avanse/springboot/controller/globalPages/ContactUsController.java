package com.avanse.springboot.controller.globalPages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.avanse.springboot.DTO.forms.contactUs.CustomerDTO;
import com.avanse.springboot.model.forms.contactUs.Customer;
import com.avanse.springboot.model.forms.contactUs.Investor;
import com.avanse.springboot.service.forms.contactUs.CustomerService;

@Controller
public class ContactUsController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/admin/contactUs/customers")
	public String getFirstCustomersPage(Model model) {

//		List<University>universities = universityService.getAllUniversity();
//		model.addAttribute("universities", universityService.getAllUniversity());

		return listCustomersByPage(1, model);
	}
	
	@GetMapping("/admin/contactUs/customers/page/{pageNum}")
	public String listCustomersByPage(@PathVariable(name = "pageNum") int pageNum, Model model) {
		Page<Customer> page = customerService.listCustomersByPage(pageNum);
		List<Customer> customers = page.getContent();
		
		System.out.println("PageNum =" + pageNum);
		System.out.println("Total elements= "+page.getNumberOfElements());
		System.out.println("Total Pages= "+page.getTotalPages());
				
		long startCount = (pageNum - 1) * customerService.CUSTOMERS_PER_PAGE + 1;
		long endCount = startCount + customerService.CUSTOMERS_PER_PAGE- 1;
		
		if(endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("customers", customers);
		return "customersPage";
	}
	
	@PostMapping("/viewDynamicPages/contactus/add")
	public String customersAddPost(@ModelAttribute("customerDTO") CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setName(customerDTO.getName());
		customer.setCity(customerDTO.getCity());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		customer.setLoanAccountNumber(customerDTO.getLoanAccountNumber());
		customer.setLoanStatus(customerDTO.getLoanStatus());
		customerService.addCustomer(customer);
		return "thankyou";
	}
	
	
}
