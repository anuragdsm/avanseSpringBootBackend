package com.avanse.springboot.controller.globalPages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.avanse.springboot.DTO.forms.applyNow.ApplyNowGeneralDTO;
import com.avanse.springboot.model.forms.applyNow.ApplyNowGeneral;
import com.avanse.springboot.service.applyNow.ApplyNowGeneralService;

@Controller
public class ApplyNowAllController {

	@Autowired
	ApplyNowGeneralService applyNowGeneralService;
	
	@PostMapping("/viewDynamicPages/apply-now/add")
	public String ApplyNowGeneralAddPost(@ModelAttribute("applyNowGeneralDTO") ApplyNowGeneralDTO applyNowGeneralDTO) {
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
		applyNowGeneralService.addApplyNowGeneral(applyNowGeneral);
		
		return "thankyou";
	}
	
	
}
