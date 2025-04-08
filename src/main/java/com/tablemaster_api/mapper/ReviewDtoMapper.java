package com.tablemaster_api.mapper;

import com.tablemaster_api.dto.LeaveReviewDto;
import com.tablemaster_api.dto.ReviewDto;
import com.tablemaster_api.entity.Review;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ReviewDtoMapper {

    ReviewDto fromEntity(Review restaurantReview);
    Review toEntity(ReviewDto restaurantReviewDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LeaveReviewDto leaveReviewDto, @MappingTarget Review restaurantReview);
}
