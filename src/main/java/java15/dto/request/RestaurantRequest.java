package java15.dto.request;

import jakarta.validation.constraints.NotBlank;
import java15.enums.RestType;
import lombok.Data;

@Data
public class RestaurantRequest {
    @NotBlank
    private String name;
    private String location;
    private RestType restType;
    private String service;

}

