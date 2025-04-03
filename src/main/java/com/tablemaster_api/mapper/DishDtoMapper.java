package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.DishDto;
import com.tablemaster_api.entity.Dish;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DishDtoMapper {

    DishDto fromEntity(Dish dish);

    Dish toEntity(DishDto dishDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DishDto dishDto, @MappingTarget Dish dish);
}
