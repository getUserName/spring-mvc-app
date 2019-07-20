package com.tropika.app.persistence;

import org.springframework.data.repository.CrudRepository;

import entities.Reservation;

public interface RsrvDAO extends CrudRepository<Reservation, Integer>{

}
