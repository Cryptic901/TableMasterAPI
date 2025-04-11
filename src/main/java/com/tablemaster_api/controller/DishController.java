package com.tablemaster_api.controller;

import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.entity.Dish;
import com.tablemaster_api.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDishById(@PathVariable long id) {
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    @PostMapping
    public ResponseEntity<DishDto> addDish(@RequestBody Dish dish) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dishService.addDish(dish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable long id) {
        return ResponseEntity.ok(dishService.deleteDish(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable long id, DishDto dishDto) {
        return ResponseEntity.ok(dishService.updateDish(id, dishDto));
    }
}

