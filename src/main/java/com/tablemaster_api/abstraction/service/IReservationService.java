package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tables;
import com.tablemaster_api.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservationService {

    List<Tables> getFreeTables(long restaurantId, LocalDateTime targetTimeStart, LocalDateTime targetTimeEnd);

    void sendReservingMessageToEmail(User user, RestaurantDto restaurant,
                                     ContactInfoDto contactInfo, Reservation reservation, Tables table);

    String reserveTable(long restaurantId, long tableId, LocalDateTime suggestedTimeStart,
                        LocalDateTime suggestedTimeEnd);
}
