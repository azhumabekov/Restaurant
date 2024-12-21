package java15.dto.request;

import jakarta.validation.constraints.NotBlank;
import java15.enums.RestType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RestaurantRequest {
    @NotBlank
    private String name;
    private String location;
    private RestType restType;
    @Length(min = 1, max = 15)
    private int numberOfEmployees;
    private String service;

}

