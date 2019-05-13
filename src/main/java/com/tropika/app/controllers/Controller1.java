package com.tropika.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller1 {

    @GetMapping("/")
    public String display() {
    	//get rooms

    	//get bookings by room for today

    	
        return "bookings_per_room_by_day";
    }

}