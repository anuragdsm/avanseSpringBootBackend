package com.avanse.springboot.model.forms.contactUs.exports;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.avanse.springboot.model.forms.contactUs.AbstractExporter;
import com.avanse.springboot.model.forms.contactUs.Customer;

public class CustomersCSVExporter extends AbstractExporter {

	public void export(List<Customer> listOfCustomers, HttpServletResponse response) throws IOException{
	
		super.setResponseHeader(response, "text/csv", ".csv", "customers_");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String [] csvHeader = {"ID", "Name", "Email", "Phone Number", "City", "Loan Account Number", "Loan Status"};
		String [] fieldMapping = {"id", "name","email","phoneNumber", "city","loanAccountNumber", "loanStatus" };
		
		csvWriter.writeHeader(csvHeader);
		
		for(Customer customer : listOfCustomers) {
			customer.setName(customer.getName().replace("--", " "));
			customer.setEmail(customer.getEmail().replace("--", " "));
			customer.setPhoneNumber(customer.getPhoneNumber().replace("--", " "));
			customer.setCity(customer.getCity().replace("--", " "));
			customer.setLoanAccountNumber(customer.getLoanAccountNumber().replace("--", " "));
			customer.setLoanStatus(customer.getLoanStatus().replace("--", " "));
			csvWriter.write(customer, fieldMapping);
		}
		csvWriter.close();
	}
}
