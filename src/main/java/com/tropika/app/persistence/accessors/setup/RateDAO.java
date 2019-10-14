package com.tropika.app.persistence.accessors.setup;

import org.springframework.data.repository.CrudRepository;

import entities.Rate;

public interface RateDAO extends CrudRepository<Rate, Integer> {

}
