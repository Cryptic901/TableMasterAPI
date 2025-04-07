package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.RestaurantReviewDto;
import com.tablemaster_api.entity.RestaurantReview;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RestaurantReviewDtoMapper {

    RestaurantReviewDto fromEntity(RestaurantReview restaurantReview);
    RestaurantReview toEntity(RestaurantReviewDto restaurantReviewDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(RestaurantReviewDto restaurantReviewDto, @MappingTarget RestaurantReview restaurantReview);
}
