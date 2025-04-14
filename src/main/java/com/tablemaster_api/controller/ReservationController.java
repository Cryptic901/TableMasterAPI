package com.tablemaster_api.controller;

import com.tablemaster_api.dto.TimeIntervalDto;
import com.tablemaster_api.entity.Tables;
import com.tablemaster_api.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/tables/free/{id}")
    public ResponseEntity<List<Tables>> getFreeTables(@PathVariable long id, @RequestBody TimeIntervalDto timeIntervalDto) {
        return ResponseEntity.ok(reservationService.getFreeTables(id, timeIntervalDto));
    }

    @PostMapping("tables/reserve/restaurant/{restaurantId}/table/{tableId}")
    public ResponseEntity<String> reserveTable(
            @PathVariable long restaurantId, @PathVariable long tableId,
            @RequestBody TimeIntervalDto timeIntervalDto, Principal principal) {
        return ResponseEntity.ok(reservationService.reserveTable(restaurantId, tableId, timeIntervalDto, principal));
    }
}
