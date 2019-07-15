package com.tropika.app.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tropika.app.service.BookingService;

import entities.Room;

@Controller
public class Controller1 {

	@Autowired BookingService bkSrv;
	
    @GetMapping("/")
    public String display(Model model) {

    	model.addAttribute("rooms", getRoomWithBookings());

        return "bookings_per_room_by_day";
    }

    private List<RoomWithBookings> getRoomWithBookings(){
    	List<RoomWithBookings> rmsBks = new ArrayList<>();
    	
    	//get rooms
    	List<Room> rooms = bkSrv.getRooms();
    	
    	//get bookings per room for today
    	Date today = new Date();
    	for(Room rm: rooms) {
    		
    		RoomWithBookings rmBks = new RoomWithBookings();
    		rmBks.id = rm.getId();
    		rmBks.roomName = rm.getName();
    		
    		List<Booking> stays = bkSrv.getStays(rm, today).stream().map(s -> {
        		Booking bk = new Booking();
        		bk.id = s.getId();
        		bk.type = BookingType.STAY;
        		bk.fullName = s.getCheckinerFamilyName();
        		bk.period = s.getCheckinDatetime().toString() + " to " + s.getCheckoutDatetime().toString();
        		bk.pax = s.getPax();
        		bk.bedType = s.getBedType();
        		
        		return bk;
        	}).collect(Collectors.toList());
    		
    		List<Booking> reservations = bkSrv.getReservations(rm, today).stream().map(s -> {
        		Booking bk = new Booking();
        		bk.id = s.getId();
        		bk.type = BookingType.RESERVATION;
        		bk.fullName = s.getReserverFamilyName();
        		bk.period = s.getCheckinDatetime().toString() + " to " + s.getCheckoutDatetime().toString();
        		bk.pax = s.getPax();
        		bk.bedType = s.getBedType();
        		
        		return bk;
        	}).collect(Collectors.toList());
    		
    		rmBks.bookings.addAll(stays);
    		rmBks.bookings.addAll(reservations);
    		
    		rmsBks.add(rmBks);
    	}

    	return rmsBks;
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
    }
    
    enum BookingType{
		STAY, RESERVATION
	}

}