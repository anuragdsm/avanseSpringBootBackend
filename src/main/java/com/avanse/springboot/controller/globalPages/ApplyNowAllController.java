package com.avanse.springboot.controller.globalPages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.avanse.springboot.DTO.forms.applyNow.ApplyNowGeneralDTO;
import com.avanse.springboot.DTO.forms.applyNow.ExecutiveEducationLoanDTO;
import com.avanse.springboot.model.forms.applyNow.ApplyNowGeneral;
import com.avanse.springboot.model.forms.applyNow.ExecutiveEducationLoan;
import com.avanse.springboot.service.applyNow.ApplyNowGeneralService;
import com.avanse.springboot.service.applyNow.ExecutiveEducationLoanService;

@Controller
public class ApplyNowAllController {

	@Autowired
	ApplyNowGeneralService applyNowGeneralService;
	
	@Autowired
	ExecutiveEducationLoanService executiveEducationLoanService;
	
	@PostMapping("/viewDynamicPages/apply-now/add")
	public String applyNowGeneralAddPost(@ModelAttribute("applyNowGeneralDTO") ApplyNowGeneralDTO applyNowGeneralDTO) {
		ApplyNowGeneral applyNowGeneral = new ApplyNowGeneral();
		applyNowGeneral.setFirstName(applyNowGeneralDTO.getFirstName());
		applyNowGeneral.setLastName(applyNowGeneralDTO.getLastName());
		applyNowGeneral.setCity(applyNowGeneralDTO.getCity());
		applyNowGeneral.setEmailId(applyNowGeneralDTO.getEmailId());
		applyNowGeneral.setContactNumber(applyNowGeneralDTO.getContactNumber());
		applyNowGeneral.setPlaceOfStudy(applyNowGeneralDTO.getPlaceOfStudy());
		applyNowGeneral.setLoanAmount(applyNowGeneralDTO.getLoanAmount());
		applyNowGeneral.setAdmissionStatus(applyNowGeneralDTO.getAdmissionStatus());
		applyNowGeneral.setPermission(applyNowGeneralDTO.getPermission());
		applyNowGeneral.setTimeOfStudy(applyNowGeneralDTO.getTimeOfStudy());
		applyNowGeneralService.addApplyNowGeneral(applyNowGeneral);
		
		return "thankyou";
	}
	
	@PostMapping("/viewDynamicPages/executive-education-loan/add")
	public String executiveEducationLoanAddPost(@ModelAttribute("executiveEducationLoanDTO") ExecutiveEducationLoanDTO executiveEducationLoanDTO) {
		ExecutiveEducationLoan executiveEducationLoan = new ExecutiveEducationLoan();
		executiveEducationLoan.setFirstName(executiveEducationLoanDTO.getFirstName());
		executiveEducationLoan.setLastName(executiveEducationLoanDTO.getLastName());
		executiveEducationLoan.setEmail(executiveEducationLoanDTO.getEmail());
		executiveEducationLoan.setPhoneNumber(executiveEducationLoanDTO.getPhoneNumber());
		executiveEducationLoan.setCourseName(executiveEducationLoanDTO.getCourseName());
		executiveEducationLoan.setLoanAmount(executiveEducationLoanDTO.getLoanAmount());
		executiveEducationLoanService.addExecutiveEducationLoan(executiveEducationLoan);
		return "thankyou";
	}
	
	
}
