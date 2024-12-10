package by.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReviewDtoRequest {

    @NotEmpty(message = "Review not should be empty")
    @Size(min = 10, max = 250, message = "Review should contain 10 - 250 characters")
    private String reviewText;

    @NotNull(message = "rank not should be empty")
    @Min(1)
    @Max(value = 5, message = "Rank should be between 1 and 5 number")
    private int rank;
}
