package by.clevertec.dto;

import by.clevertec.annotation.EnumValidator;
import by.clevertec.enums.category.CarCategory;
import by.clevertec.enums.category.CarCategoryConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDtoRequest {

    @Convert(converter = CarCategoryConverter.class)
    @NotNull(message = "BrandCar not should by empty")
    @EnumValidator(enumClass = CarCategory.class, message = "Invalid category. Must match one of: SEDAN, HATCHBACK, CROSSOVER, etc.")
    private CarCategory carCategory;
}
