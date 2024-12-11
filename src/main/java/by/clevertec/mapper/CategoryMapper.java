package by.clevertec.mapper;

import by.clevertec.dto.CategoryDtoRequest;
import by.clevertec.dto.CategoryDtoResponse;
import by.clevertec.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Category toCategory(CategoryDtoRequest categoryDtoRequest);


    CategoryDtoResponse toCategoryDtoResponse(Category category);
}
