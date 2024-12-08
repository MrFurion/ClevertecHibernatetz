package by.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CarShowroomDtoRequest {

    @NotEmpty(message = "Name not should be empty")
    @Size(min = 3, max = 20, message = "Name should by 5 - 20 characters")
    private String name;
    @NotEmpty
    @Size(min = 5, max = 20, message = "Address should by 5 - 20 characters")
    private String address;
}
