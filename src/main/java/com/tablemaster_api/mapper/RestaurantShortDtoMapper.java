package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.entity.Restaurant;

public class RestaurantShortDtoMapper {

    public static RestaurantShortDto fromEntity(Restaurant restaurant) {
        return new RestaurantShortDto(
                restaurant.getName(),
                restaurant.getLocation(),
                restaurant.getCountOfReviews(),
                restaurant.getWorkTimeOpen(),
                restaurant.getWorkTimeClosed(),
                restaurant.getRating()
        );
    }
}
