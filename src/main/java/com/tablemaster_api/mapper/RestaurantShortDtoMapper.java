package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.RestaurantShortDto;
import com.tablemaster_api.entity.Restaurant;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {TimeMapper.class})
public interface RestaurantShortDtoMapper {

    RestaurantShortDto fromEntity(Restaurant restaurant);
    Restaurant toEntity(RestaurantDto restaurantDto);

}
