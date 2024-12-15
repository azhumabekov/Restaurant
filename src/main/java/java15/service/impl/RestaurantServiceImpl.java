package java15.service.impl;

import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import java15.models.Restaurant;
import java15.repo.RestaurantRepository;
import java15.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setLocation(request.getLocation());
        restaurant.setRestType(request.getRestType());
        restaurant.setNumberOfEmployees(request.getNumberOfEmployees());
        restaurant.setService(request.getService());
        restaurantRepository.save(restaurant);
        return mapToResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return mapToResponse(restaurant);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setName(request.getName());
        restaurant.setLocation(request.getLocation());
        restaurant.setRestType(request.getRestType());
        restaurant.setNumberOfEmployees(request.getNumberOfEmployees());
        restaurant.setService(request.getService());
        restaurantRepository.save(restaurant);
        return mapToResponse(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantResponse mapToResponse(Restaurant restaurant) {
        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getLocation(),
                restaurant.getRestType(),
                restaurant.getNumberOfEmployees(),
                restaurant.getService()
        );
    }
}
