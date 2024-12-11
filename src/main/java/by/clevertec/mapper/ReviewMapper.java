package by.clevertec.mapper;

import by.clevertec.dto.ReviewDtoRequest;
import by.clevertec.dto.ReviewDtoResponse;
import by.clevertec.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "clients", ignore = true)
    Review toReview(ReviewDtoRequest reviewDtoRequest);

    ReviewDtoResponse toReviewDto(Review review);
}
