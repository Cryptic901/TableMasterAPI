package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.ReservationRepository;
import com.tablemaster_api.entity.Tables;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Tables> getFreeTables(long restaurantId, LocalDateTime targetTime) {
        return reservationRepository.getFreeTables(restaurantId)
                .stream().filter(t -> t.getReservationTime() == null ||
                        !targetTime.isAfter(t.getReservationTime()) ||
                        targetTime.isAfter(t.getReservationTime().plusHours(2)))
                .toList();
    }

    public String reserveTable(long restaurantId, long tableId) {

    }
}
