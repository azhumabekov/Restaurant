package java15.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java15.enums.RestType;
import lombok.Data;

@Data
public class RestaurantRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    @NotBlank
    private String location;
    @NotNull
    private RestType restType;
    @NotBlank
    private String service;

}

