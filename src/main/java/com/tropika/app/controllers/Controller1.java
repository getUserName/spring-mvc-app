package com.tropika.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller1 {

    @GetMapping("/")
    public String display(Model model) {

    	model.addAttribute("rooms", getRoomWithBookings());

        return "bookings_per_room_by_day";
    }

    private List<RoomWithBookings> getRoomWithBookings(){
    	//get rooms

    	//get bookings for today

    }

    class RoomWithBookings {
    	int id;
    	String roomName;
    	List<Booking> bookings;
    }

    class Booking{
    	int id;
    	BookingType type;
    	String fullName;
    	String period;
    	int pax;
    	String bedType;

    	enum BookingType{
    		STAY, RESERVATION
    	}
    }

}