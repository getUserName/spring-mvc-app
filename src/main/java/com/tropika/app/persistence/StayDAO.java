package com.tropika.app.persistence;

import org.springframework.data.repository.CrudRepository;

import entities.Stay;

public interface StayDAO extends CrudRepository<Stay, Integer>{

}
