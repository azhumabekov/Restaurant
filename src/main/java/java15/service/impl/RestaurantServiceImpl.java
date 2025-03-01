package java15.service.impl;

import jakarta.validation.ValidationException;
import java15.dto.request.RestaurantRequest;
import java15.dto.response.RestaurantResponse;
import java15.exceptions.NotFoundException;
import java15.models.Employee;
import java15.models.Restaurant;
import java15.repository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        if (restaurantRepository.existsByName(request.getName()) &&
                restaurantRepository.existsByLocation(request.getLocation())) {
            throw new ValidationException("Точно такой ресторан с таким адресом уже существует");
        }
        if (request.getName() == null || request.getLocation() == null) {
            throw new ValidationException("Название и локация ресторана обязательны");
        }
        log.info("Создание нового ресторана: {}", request);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setLocation(request.getLocation());
        restaurant.setRestType(request.getRestType());
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
        restaurant.setService(request.getService());

        restaurantRepository.save(restaurant);
        log.info("Ресторан успешно обновлён: {}", restaurant);

        return mapToResponse(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        List<Employee> employees = employeeRepository.findByRestaurant(restaurant);

        for (Employee employee : employees) {
            employee.setRestaurant(null);
        }

        employeeRepository.saveAll(employees);

        restaurantRepository.delete(restaurant);

        log.info("Restaurant {} has been removed. {} employees reassigned", restaurant.getName(), employees.size());
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