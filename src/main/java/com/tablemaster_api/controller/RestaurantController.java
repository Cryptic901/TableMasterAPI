package com.tablemaster_api.controller;

import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.dto.UpdateRestaurantDto;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.service.RestaurantService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}/contactInfo")
    public ResponseEntity<ContactInfoDto> getContactInfo(@PathVariable long id) {
        return ResponseEntity.ok(restaurantService.getContactInfo(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantShortDto>> getAllRestaurants(
            @RequestParam(required = false, defaultValue = "countOfReviews") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order) {
        Sort.Order sortOrder = new Sort.Order(order.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC,
                sortBy);
        Sort sort = Sort.by(sortOrder);
        return ResponseEntity.ok(restaurantService.getAllSorted(sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurantService.addRestaurant(restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable long id) {
        return ResponseEntity.ok(restaurantService.deleteRestaurant(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable long id, @RequestBody UpdateRestaurantDto restaurantDto) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, restaurantDto));
    }
}
