package com.tropika.app.web.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tropika.app.aop.annotations.LogExecution;
import com.tropika.app.service.booking.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Controller1 {

	@Autowired
	private BookingService bkSrv;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
    @GetMapping("/") @LogExecution
    public String display(Model model, @RequestParam(name="date", required=false) String dateParam) {

    	LocalDate date = convertDateParam(dateParam);
    	
    	model.addAttribute("rooms", bkSrv.getRoomWithBookings(date));

        return "bookings_per_room_by_day";
    }
    
    private LocalDate convertDateParam(String dateParam) {
    	try {
    		if(dateParam==null) return LocalDate.now();
    		else return LocalDate.parse(dateParam, formatter);
    	}
    	catch(Exception e) {
    		log.error(e.getMessage());
    		return LocalDate.now();
    	}
    }
}