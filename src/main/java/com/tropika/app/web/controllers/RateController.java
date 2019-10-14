package com.tropika.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tropika.app.aop.annotations.LogExecution;
import com.tropika.app.service.booking.DTO.Rate;
import com.tropika.app.service.booking.SetupService2;

@Controller
public class RateController {
	
	@Autowired
	private SetupService2 setupSrv;
	
	@GetMapping("/rates") @LogExecution
    public String rates(Model model) {
    	model.addAttribute("rates", setupSrv.getRates());
    	return "rates";
    }
    
    @GetMapping("/addrate") @LogExecution
    public String addRate(Model model, Rate rate) {
    	setupSrv.addRate(rate);
    	return rates(model);
    }
    
    @GetMapping("/removerate") @LogExecution
    public String removeRate(Model model, Rate rate) {
    	setupSrv.removeRate(rate);
    	return rates(model);
    }
    
    @GetMapping("/updaterate") @LogExecution
    public String updateRate(Model model, Rate rate) {
    	setupSrv.updateRate(rate);
    	return rates(model);
    }
}
