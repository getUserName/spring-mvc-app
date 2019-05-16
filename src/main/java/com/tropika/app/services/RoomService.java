package com.tropika.app.services;

@Service
public class RoomService {
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