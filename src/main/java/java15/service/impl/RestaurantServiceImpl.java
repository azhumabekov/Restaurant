package java15.service.impl;

import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import java15.exceptions.NotFoundException;
import java15.models.Restaurant;
import java15.repository.RestaurantRepository;
import java15.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            log.error("Restaurant name cannot be empty");
        }
        log.info("Создание нового ресторана: {}", request);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setLocation(request.getLocation());
        restaurant.setRestType(request.getRestType());
        restaurant.setNumberOfEmployees(request.getNumberOfEmployees());
        restaurant.setService(request.getService());

        restaurantRepository.save(restaurant);
        log.info("Ресторан успешно создан: {}", restaurant);

        return mapToResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        log.info("Получение всех ресторанов");
        return restaurantRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {
        log.info("Получение ресторана по ID: {}", id);

        Restaurant restaurant = findRestaurantById(id);

        return mapToResponse(restaurant);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {
        log.info("Обновление ресторана ID: {} данными: {}", id, request);

        Restaurant restaurant = findRestaurantById(id);

        restaurant.setName(request.getName());
        restaurant.setLocation(request.getLocation());
        restaurant.setRestType(request.getRestType());
        restaurant.setNumberOfEmployees(request.getNumberOfEmployees());
        restaurant.setService(request.getService());

        restaurantRepository.save(restaurant);
        log.info("Ресторан успешно обновлён: {}", restaurant);

        return mapToResponse(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        log.info("Удаление ресторана по ID: {}", id);

        if (!restaurantRepository.existsById(id)) {
            log.error("Ресторан с ID {} не найден", id);
            throw new NotFoundException("Restaurant not found with ID: " + id);
        }


        restaurantRepository.deleteById(id);
        log.info("Ресторан с ID {} успешно удалён", id);
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

    private Restaurant findRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Ресторан с ID {} не найден", id);
                    return new NotFoundException("Restaurant not found with ID: " + id);
                });
    }
}