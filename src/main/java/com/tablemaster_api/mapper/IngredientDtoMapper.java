package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Ingredient;

public class IngredientDtoMapper {

    public static IngredientDto fromEntity(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getName()
        );
    }
}
