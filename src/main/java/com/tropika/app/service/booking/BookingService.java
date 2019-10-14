package com.tropika.app.service.booking;

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
	
	public List<Room> getRooms(){
		return roomDAO.findAll();
	}
	
	public void addRoom(Room room) {
		roomDAO.save(room);
	}
	
    public List<RoomWithBookings> getRoomWithBookings(LocalDate _date){
    	log.info("Getting bookings.");
    	List<RoomWithBookings> rmsBks = new ArrayList<>();
    	
    	//get rooms
    	List<Room> rooms = roomDAO.findAll();
    	
    	//get bookings per room for provided date
    	LocalDateTime date = _date.atStartOfDay();
    	LocalDateTime nextDay = date.plusDays(1);
    	
    	for(Room rm: rooms) {
    		
    		RoomWithBookings rmBks = new RoomWithBookings();
    		rmBks.setId(rm.getId());
    		rmBks.setRoomName(rm.getName());
    		rmBks.setBookings(new ArrayList<>());
    		
    		List<Booking> stays = stayDAO.findByRoomAndDay(rm, date, nextDay).stream().map(s -> {
        		Booking bk = new Booking();
        		bk.setId(s.getId());
        		bk.setType(BookingType.STAY);
        		bk.setFullName(s.getCheckinerFamilyName());
        		bk.setPeriod(s.getCheckinDatetime().format(formatter) + " to " + s.getCheckoutDatetime().format(formatter));
        		bk.setPax(s.getPax());
        		bk.setBedType(s.getBedType());
        		
        		return bk;
        	}).collect(Collectors.toList());
    		
    		List<Booking> reservations = rsrvDAO.findByRoomAndDay(rm, date, nextDay).stream().map(s -> {
        		Booking bk = new Booking();
        		bk.setId(s.getId());
        		bk.setType(BookingType.RESERVATION);
        		bk.setFullName(s.getReserverFamilyName());
        		bk.setPeriod(s.getCheckinDatetime().format(formatter) + " to " + s.getCheckoutDatetime().format(formatter));
        		bk.setPax(s.getPax());
        		bk.setBedType(s.getBedType());
        		
        		return bk;
        	}).collect(Collectors.toList());

    		rmBks.getBookings().addAll(stays);
    		rmBks.getBookings().addAll(reservations);
    		
    		rmsBks.add(rmBks);
    	}

    	return rmsBks;
    }
}
