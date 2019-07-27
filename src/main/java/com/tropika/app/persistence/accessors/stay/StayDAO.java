package com.tropika.app.persistence.accessors.stay;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import entities.Room;
import entities.Stay;

public interface StayDAO extends CrudRepository<Stay, Integer>{
	@Query("FROM #{#entityName} AS x WHERE "
			+ "x.room=:room AND "
			+ "("
			+ "x.checkinDatetime BETWEEN :start AND :end OR "
			+ "x.checkoutDatetime BETWEEN :start AND :end"
			+ ")")
	List<Stay> findByRoomAndDay(@Param("room") Room r, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
