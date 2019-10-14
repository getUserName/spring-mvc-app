package com.tropika.app.service.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tropika.app.persistence.accessors.setup.BedTypeDAO;
import com.tropika.app.service.booking.DTO.BedType;
import com.tropika.app.service.booking.DTO.Rate;
import com.tropika.app.service.booking.DTO.Room;

@Service
public class DTOConverter {

	@Autowired
	private BedTypeDAO bedTypeDAO;
	
	public void copyIntoEntity(Room dto, entities.Room entity){
		entity.setName(dto.getName());
		entity.setBedType(bedTypeDAO.findById(dto.getBedType().getId()).get());
		entity.setStatus(dto.getStatus());
	}
	
	public Room toDTO(entities.Room entity) {
		Room dto = new Room();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setBedType(toDTO(entity.getBedType()));
		dto.setStatus(entity.getStatus());
		return dto;
		
	}
	
	public void copyIntoEntity(BedType dto, entities.BedType entity) {
		entity.setName(dto.getName());
		entity.setStatus(dto.getStatus());
	}
	
	public BedType toDTO(entities.BedType entity) {
		BedType dto= new BedType();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		return dto;
	}
	
	public void copyIntoEntity(Rate dto, entities.Rate entity) {
		entity.setName(dto.getName());
		entity.setWeekdayRate(dto.getWeekdayRate());
		entity.setWeekendRate(dto.getWeekendRate());
		entity.setStatus(dto.getStatus());
	}
	
	public Rate toDTO(entities.Rate entity) {
		Rate dto= new Rate();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setWeekdayRate(entity.getWeekdayRate());
		dto.setWeekendRate(entity.getWeekendRate());
		dto.setStatus(entity.getStatus());
		return dto;
	}
}
