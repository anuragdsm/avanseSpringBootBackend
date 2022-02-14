package com.avanse.springboot.model;

import lombok.Data;

@Data
public class EligibilityExam {
	
	private Long id;
	private String name;
	private int requiredScore;

}
