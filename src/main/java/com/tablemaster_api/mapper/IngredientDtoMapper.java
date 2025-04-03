package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.dto.IngredientDto;
import com.tablemaster_api.entity.Dish;
import com.tablemaster_api.entity.Ingredient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IngredientDtoMapper {

    IngredientDto fromEntity(Ingredient ingredient);

    Ingredient toEntity(IngredientDto ingredientDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(IngredientDto ingredientDto, @MappingTarget Ingredient ingredient);
}

