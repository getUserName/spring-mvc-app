package com.tropika.app.persistence.accessors;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import entities.BedType;

public interface BedTypeDAO extends CrudRepository<BedType, Integer> {
	List<BedType> findAll();
}
