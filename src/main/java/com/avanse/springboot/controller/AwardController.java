package com.avanse.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.avanse.springboot.service.AwardService;

@RestController
public class AwardController {
	@Autowired
	AwardService awardService;

}
