package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.UpdateRestaurantDto;
import com.tablemaster_api.entity.Restaurant;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestaurantDtoMapper {

    RestaurantDto fromEntity(Restaurant restaurant);

    Restaurant toEntity(RestaurantDto restaurantDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateRestaurantDto restaurantDto, @MappingTarget Restaurant restaurant);
}

