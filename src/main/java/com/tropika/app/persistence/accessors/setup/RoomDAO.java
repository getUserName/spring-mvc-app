package com.tropika.app.persistence.accessors.setup;

import org.springframework.data.repository.CrudRepository;

import entities.Room;

public interface RoomDAO extends CrudRepository<Room, Integer> {
}
