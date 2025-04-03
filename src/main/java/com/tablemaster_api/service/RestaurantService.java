package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.RestaurantRepository;
import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.mapper.RestaurantShortDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public ContactInfoDto getContactInfo(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        return new ContactInfoDto(restaurant.getPhone(), restaurant.getEmail());
    }

    public List<RestaurantShortDto> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream().map(RestaurantShortDtoMapper::fromEntity).toList();
    }
}
