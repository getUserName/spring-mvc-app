package com.tropika.app.persistence.accessors.setup;

import org.springframework.data.repository.CrudRepository;

import entities.BedType;

public interface BedTypeDAO extends CrudRepository<BedType, Integer> {
}
