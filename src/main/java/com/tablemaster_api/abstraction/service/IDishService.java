package com.tablemaster_api.abstraction.service;

import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.entity.Dish;

import java.util.List;
import java.util.Map;

public interface IDishService {

    List<DishDto> getAllDishes();

    DishDto getDishById(long dishId);

    Dish addDish(DishDto dish);

    String deleteDish(long dishId);

    DishDto updateDish(long dishId, DishDto dishDto);
}
