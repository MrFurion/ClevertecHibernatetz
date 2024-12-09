package by.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClientDtoRequest {

    @NotEmpty(message = "Name not should be empty")
    @Size(min = 5, max = 20, message = "Name should be between 5 and 20 characters")
    private String name;

    @NotNull(message = "Contact set should not be null")
    @NotEmpty(message = "Contact set should not be empty")
    private Set<@Pattern(regexp = "^\\+?[0-9]{1,4}?[\\s\\-]?[0-9]+[\\s\\-]?[0-9]+$", message = "Invalid phone number") String> contact = new HashSet<>();
}
