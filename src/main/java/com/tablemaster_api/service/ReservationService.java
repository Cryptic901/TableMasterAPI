package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.ReservationRepository;
import com.tablemaster_api.abstraction.repository.RestaurantRepository;
import com.tablemaster_api.abstraction.repository.TablesRepository;
import com.tablemaster_api.abstraction.repository.UserRepository;
import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.entity.Tables;
import com.tablemaster_api.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TablesRepository tablesRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, TablesRepository tablesRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.tablesRepository = tablesRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public List<Tables> getFreeTables(long restaurantId, LocalDateTime targetTimeStart, LocalDateTime targetTimeEnd) {
        return reservationRepository.getFreeTables(restaurantId, targetTimeStart, targetTimeEnd);
    }

    @Transactional
    public String reserveTable(long restaurantId, long tableId, LocalDateTime suggestedTimeStart,
                               LocalDateTime suggestedTimeEnd) {

        Tables table = tablesRepository.findById(tableId)
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!getFreeTables(restaurantId, suggestedTimeStart, suggestedTimeEnd).contains(table)) {
            return "This table is reserved at this time.";
        }
        Reservation reservation = new Reservation();
        reservation.setTables(table);
        reservation.setRestaurant(restaurant);
        reservation.setUser(user);
        reservation.setReservationTimeStart(suggestedTimeStart);
        reservation.setReservationTimeEnd(suggestedTimeEnd);
        //TODO send message to email
        reservationRepository.save(reservation);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String reservedTime = suggestedTimeStart.format(timeFormatter) + "-"
                + suggestedTimeEnd.format(timeFormatter);
        String reservedDay = suggestedTimeStart.format(dateFormatter);

        return "Table reserved successfully on time: " + reservedTime + ",\n On date: " + reservedDay;
    }
}
