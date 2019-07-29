package com.tropika.app.service.booking;

import java.util.List;

import lombok.Data;

@Data
class RoomWithBookings {
	int id;
	String roomName;
	List<Booking> bookings;
}

@Data
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