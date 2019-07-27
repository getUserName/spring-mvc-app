package com.tropika.app.persistence.accessors.room;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import entities.Room;

public interface RoomDAO extends CrudRepository<Room, Integer> {
	List<Room> findAll();
}
