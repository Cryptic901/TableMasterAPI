package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
    ContactInfoDto getContactInfo(long restaurantId);

    List<RestaurantShortDto> getAllRestaurants();

    RestaurantDto getRestaurantById(long restaurantId);

    String createRestaurant(Restaurant restaurant);

    String deleteRestaurant(long restaurantId);

    RestaurantDto updateRestaurant(long restaurantId, RestaurantDto restaurantDto);
}
