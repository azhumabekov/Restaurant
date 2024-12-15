package java15.dto.request;

import java15.enums.RestType;
import lombok.Data;

@Data
public class RestaurantRequest {
    private String name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private String service;

}

