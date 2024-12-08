package by.clevertec.dto;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.enums.car.CarBrandConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Convert;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CarDtoRequest {

    @NotEmpty(message = "Model not should by empty")
    @Size(min = 5, max = 20, message = "Model should by 5 - 20 characters")
    private String model;

    @Convert(converter = CarBrandConverter.class)
    @NotNull(message = "BrandCar not should by empty")
    private CarBrand brandCar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate yearOfProduction;
    @NotEmpty
    @Size(min = 1, max = 1000000)
    private String price;
}
