package com.avanse.springboot.model.forms.contactUs.exports;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.avanse.springboot.model.forms.contactUs.AbstractExporter;
import com.avanse.springboot.model.forms.contactUs.Institute;

public class InstitutesCSVExporter extends AbstractExporter {

	public void export (List<Institute> listOfInstitutes, HttpServletResponse response) throws IOException{
		super.setResponseHeader(response, "text/csv", ".csv", "institutes_");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String [] csvHeader= {"ID", "Name of Person", "Institute", "Phone Number", "Contact-Email", "City", "SubjectLine", "Loan Type"};
		String [] fieldMapping = {"id", "nameOfPerson", "nameOfInstitute", "phoneNumber", "email", "city", "subjectLine","loanType"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Institute institute : listOfInstitutes) {
			institute.setNameOfPerson(institute.getNameOfPerson().replace("--", " "));
			institute.setNameOfInstitute(institute.getNameOfInstitute().replace("--", " "));
			institute.setPhoneNumber(institute.getPhoneNumber().replace("--", " "));
			institute.setEmail(institute.getEmail().replace("--", " "));
			institute.setCity(institute.getCity().replace("--", " "));
			institute.setSubjectLine(institute.getSubjectLine().replace("--", " "));
			institute.setLoanType(institute.getLoanType().replace("--", " "));
			csvWriter.write(institute, fieldMapping);
			
			
		}
		
		csvWriter.close();

		
		
	}
}
