package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.TimeIntervalDto;
import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Tables;
import com.tablemaster_api.entity.User;

import java.security.Principal;
import java.util.List;

public interface IReservationService {

    List<Tables> getFreeTables(long restaurantId, TimeIntervalDto timeIntervalDto);

    void sendReservingMessageToEmail(User user, RestaurantDto restaurant,
                                     ContactInfoDto contactInfo, Reservation reservation, Tables table);

    String reserveTable(long restaurantId, long tableId, TimeIntervalDto timeIntervalDto, Principal principal);
}
