package com.tropika.app.service.booking;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

public class DTO {
	
	enum BookingType{
		STAY, RESERVATION
	}
	
	@Data
	public static class RoomWithBookings {
		private int id;
		private String roomName;
		private List<Booking> bookings;
	}
	
	@Data
	public static class Booking{
		private int id;
		private BookingType type;
		private String fullName;
		private String period;
		private int pax;
		private String bedType;	
	}
	
	@Data
	public static class Room{
		private int id;
		private String name;
		private BedType bedType;
		private String status;
	}
	
	@Data
	public static class BedType{
		private int id;
		private String name;
		private String status;
	}
	
	@Data
	public static class Rate{
		private int id;
		private String name;
		private BigDecimal weekdayRate;
		private BigDecimal weekendRate;
		private String status;
	}
}