package com.tablemaster_api.abstraction.repository;


import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT t FROM Tables t" +
            " WHERE t.reserved = FALSE " +
            "AND t.restaurant = :restaurantId ")
    List<Tables> getFreeTables(@Param("restaurantId") long restaurantId);

}
