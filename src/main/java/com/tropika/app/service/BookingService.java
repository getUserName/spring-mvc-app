package com.tropika.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import entities.Reservation;
import entities.Room;
import entities.Stay;

@Service
public class BookingService {
	
	public List<Room> getRooms(){
		List<Room> rooms = new ArrayList<>();
    	
    	return rooms;
	}
	
	public List<Stay> getStays(Room room, Date day){
		List<Stay> stays = new ArrayList<>();
		
		
		
		return stays;
	}
	
	public List<Reservation> getReservations(Room room, Date day){
		List<Reservation> reservations = new ArrayList<>();
		
		
		
		return reservations;
	}
}
