package com.tropika.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropika.app.persistence.accessors.reservation.RsrvDAO;
import com.tropika.app.persistence.accessors.room.RoomDAO;
import com.tropika.app.persistence.accessors.stay.StayDAO;

import entities.Room;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingService {
	@Autowired
	RsrvDAO rsrvDAO;
	@Autowired
	StayDAO stayDAO;
	@Autowired
	RoomDAO roomDAO;
	
	private static DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("MM/dd/yyyy hh:mm:ssa").parseDefaulting(ChronoField.HOUR_OF_DAY, 0).toFormatter();
	
    public List<RoomWithBookings> getRoomWithBookings(){
    	List<RoomWithBookings> rmsBks = new ArrayList<>();
    	
    	//get rooms
    	List<Room> rooms = roomDAO.findAll();
    	
    	//get bookings per room for today
    	LocalDateTime today = LocalDate.now().atStartOfDay();
    	LocalDateTime tomorrow = today.plusDays(1);
    	
    	for(Room rm: rooms) {
    		
    		RoomWithBookings rmBks = new RoomWithBookings();
    		rmBks.id = rm.getId();
    		rmBks.roomName = rm.getName();
    		rmBks.bookings = new ArrayList<>();
    		
    		List<Booking> stays = stayDAO.findByRoomAndDay(rm, today, tomorrow).stream().map(s -> {
        		Booking bk = new Booking();
        		bk.id = s.getId();
        		bk.type = BookingType.STAY;
        		bk.fullName = s.getCheckinerFamilyName();
        		bk.period = s.getCheckinDatetime().format(formatter) + " to " + s.getCheckoutDatetime().format(formatter);
        		bk.pax = s.getPax();
        		bk.bedType = s.getBedType();
        		
        		return bk;
        	}).collect(Collectors.toList());
    		
    		List<Booking> reservations = rsrvDAO.findByRoomAndDay(rm, today, tomorrow).stream().map(s -> {
        		Booking bk = new Booking();
        		bk.id = s.getId();
        		bk.type = BookingType.RESERVATION;
        		bk.fullName = s.getReserverFamilyName();
        		bk.period = s.getCheckinDatetime().format(formatter) + " to " + s.getCheckoutDatetime().format(formatter);
        		bk.pax = s.getPax();
        		bk.bedType = s.getBedType();
        		
        		return bk;
        	}).collect(Collectors.toList());
    		
    		log.info("stays = "+(
    								(stays==null)?
    									("null"):
										("list of "+stays.size())
								)
    				);
    		rmBks.bookings.addAll(stays);
    		rmBks.bookings.addAll(reservations);
    		
    		rmsBks.add(rmBks);
    	}

    	return rmsBks;
    }

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
}
