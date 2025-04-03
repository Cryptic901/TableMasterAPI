package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.ReservationRepository;
import com.tablemaster_api.abstraction.repository.RestaurantRepository;
import com.tablemaster_api.abstraction.service.IRestaurantService;
import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.entity.Tables;
import com.tablemaster_api.mapper.RestaurantDtoMapper;
import com.tablemaster_api.mapper.RestaurantShortDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDtoMapper restaurantDtoMapper;
    private final RestaurantShortDtoMapper restaurantShortDtoMapper;
    private final ReservationRepository reservationRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantDtoMapper restaurantDtoMapper, RestaurantShortDtoMapper restaurantShortDtoMapper, ReservationRepository reservationRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantDtoMapper = restaurantDtoMapper;
        this.restaurantShortDtoMapper = restaurantShortDtoMapper;
        this.reservationRepository = reservationRepository;
    }

    public ContactInfoDto getContactInfo(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        return new ContactInfoDto(restaurant.getPhone(), restaurant.getEmail());
    }

    public List<Tables> getFreeTables(long restaurantId) {
        return reservationRepository.getFreeTables(restaurantId);
    }

    public List<RestaurantShortDto> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream().map(restaurantShortDtoMapper::fromEntity).toList();
    }

    public RestaurantDto getRestaurantById(long restaurantId) {
        return restaurantDtoMapper.fromEntity(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found")));
    }

    public String createRestaurant(Restaurant restaurant) {
        try {
            restaurantRepository.save(restaurant);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Dish created successfully!";
    }

    public String deleteRestaurant(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        restaurantRepository.delete(restaurant);
        return "Restaurant deleted successfully!";
    }

    public RestaurantDto updateRestaurant(long restaurantId, RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        restaurantDtoMapper.updateEntityFromDto(restaurantDto, restaurant);
        restaurantRepository.save(restaurant);
        return restaurantDtoMapper.fromEntity(restaurant);
    }
}
