package java15.service;

import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import java15.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse createRestaurant(RestaurantRequest request);

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurantById(Long id);

    RestaurantResponse updateRestaurant(Long id, RestaurantRequest request);

    void deleteRestaurant(Long id);

    RestaurantResponse mapToResponse(Restaurant restaurant);
}
