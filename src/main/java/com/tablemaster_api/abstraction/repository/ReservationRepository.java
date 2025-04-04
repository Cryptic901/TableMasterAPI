package com.tablemaster_api.abstraction.repository;


import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT t FROM Tables t" +
            " WHERE t.restaurant = :restaurantId " +
            "AND NOT EXISTS (" +
            "SELECT r FROM Reservation r WHERE r.tables = t AND " +
            "(:targetTimeStart < r.reservationTimeEnd AND :targetTimeEnd > r.reservationTimeStart) AND " +
            "(r.reservationTimeEnd < Restaurant.workTimeClosed AND r.reservationTimeStart > Restaurant.workTimeOpen))")
    List<Tables> getFreeTables(@Param("restaurantId") long restaurantId,
                               @Param("targetTimeStart") LocalDateTime targetTimeStart,
                               @Param("targetTimeEnd") LocalDateTime targetTimeEnd);
}
