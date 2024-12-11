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
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CarDtoResponse {

    private String model;

    @Convert(converter = CarBrandConverter.class)
    private CarBrand brandCar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfProduction;
    private String price;
}
