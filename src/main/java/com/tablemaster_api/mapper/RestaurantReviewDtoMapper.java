package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.RestaurantReviewDto;
import com.tablemaster_api.entity.RestaurantReview;
import org.springframework.stereotype.Component;

@Component
public class RestaurantReviewDtoMapper {

    public RestaurantReviewDto fromEntity(RestaurantReview review) {
        return new RestaurantReviewDto(
                review.getId(),
                review.getRestaurant(),
                review.getUser(),
                review.getRating(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
    public RestaurantReview toEntity(RestaurantReviewDto dto) {
        return new RestaurantReview(
                dto.id(),
                dto.restaurant(),
                dto.user(),
                dto.rating(),
                dto.content(),
                dto.createdAt()
        );
    }
}
