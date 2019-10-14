package com.tropika.app.service.booking;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
class RoomWithBookings {
	private int id;
	private String roomName;
	private List<Booking> bookings;
}

@Data
class Booking{
	private int id;
	private BookingType type;
	private String fullName;
	private String period;
	private int pax;
	private String bedType;	
}

@Data
@Builder
class Room{
	private int id;
	private String name;
	private BedType bedType;
	private String status;
}

@Data
class BedType{
	private int id;
	private String name;
}

enum BookingType{
	STAY, RESERVATION
}