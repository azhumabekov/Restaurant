package java15.dto.response;

import java15.enums.RestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RestaurantResponse {
    private Long id;
    private String name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private String service;

}