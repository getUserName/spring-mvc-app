package com.tropika.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tropika.app.service.BookingService;

@Controller
public class Controller1 {

	@Autowired BookingService bkSrv;
	
    @GetMapping("/")
    public String display(Model model) {

    	model.addAttribute("rooms", bkSrv.getRoomWithBookings());

        return "bookings_per_room_by_day";
    }



}