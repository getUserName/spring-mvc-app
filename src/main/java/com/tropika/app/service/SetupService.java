package com.tropika.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropika.app.persistence.accessors.setup.BedTypeDAO;
import com.tropika.app.persistence.accessors.setup.RateDAO;
import com.tropika.app.persistence.accessors.setup.RoomDAO;
import com.tropika.app.service.booking.DTO;
import com.tropika.app.service.booking.DTOConverter;

import entities.BedType;
import entities.Rate;
import entities.Room;

@Service
public class SetupService {
	@Autowired
	RoomDAO roomDAO;
	@Autowired
	BedTypeDAO bedTypeDAO;
	@Autowired
	RateDAO rateDAO;
	@Autowired
	private DTOConverter converter;

	public List<DTO.BedType> getBedTypes(){
		List<DTO.BedType> dtoList = new ArrayList<>();
		bedTypeDAO.findAll().forEach(entity -> converter.toDTO(entity));
		return dtoList;
	}

	public void addBedType(DTO.BedType dto) {
		BedType entity = new BedType();
		converter.copyIntoEntity(dto, entity);
		bedTypeDAO.save(entity);
	}

	public void updateBedType(DTO.BedType dto) {
		BedType entity = bedTypeDAO.findById(dto.getId()).get();
		converter.copyIntoEntity(dto, entity);
		bedTypeDAO.save(entity);
	}

	public void removeBedType(DTO.BedType dto) {
		bedTypeDAO.delete(bedTypeDAO.findById(dto.getId()).get());
	}

	public List<DTO.Room> getRooms(){
		List<DTO.Room> dtoList = new ArrayList<>();
    	roomDAO.findAll().forEach(entity -> converter.toDTO(entity));
		return dtoList;
	}

	public void addRoom(DTO.Room dto) {
		Room entity = new Room();
		converter.copyIntoEntity(dto, entity);
		roomDAO.save(entity);
	}

	public void updateRoom(DTO.Room dto) {
		Room entity = roomDAO.findById(dto.getId()).get();
		converter.copyIntoEntity(dto, entity);
		roomDAO.save(entity);
	}

	public void removeRoom(DTO.Room dto) {
		roomDAO.delete(roomDAO.findById(dto.getId()).get());
	}

	public List<DTO.Rate> getRates(){
		List<DTO.Rate> dtoList = new ArrayList<>();
		rateDAO.findAll().forEach(entity -> converter.toDTO(entity));
		return dtoList;
	}

	public void addRate(DTO.Rate dto) {
		Rate entity = new Rate();
		converter.copyIntoEntity(dto, entity);
		rateDAO.save(entity);
	}

	public void updateRate(DTO.Rate dto) {
		Rate entity = rateDAO.findById(dto.getId()).get();
		converter.copyIntoEntity(dto, entity);
		rateDAO.save(entity);
	}

	public void removeRate(DTO.Rate dto) {
		rateDAO.delete(rateDAO.findById(dto.getId()).get());
	}
}
