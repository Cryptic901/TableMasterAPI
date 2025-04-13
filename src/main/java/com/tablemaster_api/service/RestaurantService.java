package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.RestaurantRepository;
import com.tablemaster_api.abstraction.service.IRestaurantService;
import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.mapper.RestaurantDtoMapper;
import com.tablemaster_api.mapper.RestaurantShortDtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDtoMapper restaurantDtoMapper;
    private final RestaurantShortDtoMapper restaurantShortDtoMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantDtoMapper restaurantDtoMapper,
                             RestaurantShortDtoMapper restaurantShortDtoMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantDtoMapper = restaurantDtoMapper;
        this.restaurantShortDtoMapper = restaurantShortDtoMapper;
    }

    @Cacheable(value = "restaurants", key = "#restaurantId")
    public ContactInfoDto getContactInfo(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        return new ContactInfoDto(restaurant.getPhone(), restaurant.getEmail());
    }

    public List<RestaurantShortDto> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream().map(restaurantShortDtoMapper::fromEntity).toList();
    }

    @Cacheable(value = "restaurants", key = "#restaurantId")
    public RestaurantDto getRestaurantById(long restaurantId) {
        return restaurantDtoMapper.fromEntity(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found")));
    }
    @CacheEvict(value = "restaurants", key = "#restaurant.id")
    public RestaurantDto addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurantDtoMapper.fromEntity(restaurant);
    }

    @Caching(evict = {
            @CacheEvict(value = "restaurants", key = "#restaurantId"),
            @CacheEvict(value = "restaurants", allEntries = true)
    })
    @Transactional
    public String deleteRestaurant(long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        restaurantRepository.delete(restaurant);
        return "Restaurant deleted successfully!";
    }

    @CacheEvict(value = "restaurants", key = "#restaurantId")
    public RestaurantDto updateRestaurant(long restaurantId, RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        restaurantDtoMapper.updateEntityFromDto(restaurantDto, restaurant);
        restaurantRepository.save(restaurant);
        return restaurantDtoMapper.fromEntity(restaurant);
    }

    public List<RestaurantShortDto> getAllSorted(Sort sort) {
        List<Restaurant> restaurants = restaurantRepository.findAll(sort);
        return restaurants.stream()
                .map(restaurantShortDtoMapper::fromEntity)
                .toList();
    }
}
