package com.tropika.app.persistence.accessors.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import entities.Reservation;
import entities.Room;

public interface RsrvDAO extends CrudRepository<Reservation, Integer>{
	@Query("FROM #{#entityName} AS x WHERE "
			+ "x.room=:room AND "
			+ "("
			+ "x.checkinDatetime BETWEEN :start AND :end OR "
			+ "x.checkoutDatetime BETWEEN :start AND :end"
			+ ") AND "
			+ "x.status='NOT CHECKED IN'")
	List<Reservation> findByRoomAndDay(@Param("room") Room r, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
