package com.avanse.springboot.model.forms.contactUs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class AbstractExporter {

	public void setResponseHeader(HttpServletResponse response, String contentType, String extention, String prefix)
			throws IOException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timeStamp = dateFormatter.format(new Date());
		String filenName = prefix+timeStamp+extention;
		
		response.setContentType(contentType);
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; fileName = "+ filenName;
		response.setHeader(headerKey, headerValue);
		

	}

}
