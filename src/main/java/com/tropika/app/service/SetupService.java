package com.tropika.app.service;

import java.util.List;

import javax.transaction.NotSupportedException;

import org.springframework.stereotype.Service;

import entities.BedType;
import entities.Rate;
import entities.Room;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SetupService {
	public List<BedType> listBedTypes() throws NotSupportedException{
		throw new NotSupportedException();
	}
	public void addBedType(BedType newedType) {}
	public void editBedType(BedType bedType) {}
	public void deleteBedType(BedType bedType) {}
	
	public List<Rate> listRates() throws NotSupportedException{
		throw new NotSupportedException();
	}
	public void addRate(Rate newRate) {}
	public void editRate(Rate rate) {}
	public void deleteRate(Rate rate) {}
	
	public List<Room> listRooms() throws NotSupportedException{
		throw new NotSupportedException();
	}
	public void addRoom(Room newRoom) {}
	public void editRoom(Room room) {}
	public void deleteRoom(Room room) {}
}
