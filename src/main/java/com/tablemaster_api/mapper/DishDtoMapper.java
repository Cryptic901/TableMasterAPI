package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.entity.Dish;

public class DishDtoMapper {

    public static DishDto fromEntity(Dish dish) {
        return new DishDto(
                dish.getName(),
                dish.getPrice(),
                dish.getIngredients()
        );
    }
}
