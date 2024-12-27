package java15.service.impl;

import java15.dto.request.ChequeRequest;
import java15.dto.response.ChequeResponse;
import java15.dto.response.MenuItemResponse;
import java15.exceptions.ResourceNotFoundException;
import java15.models.Cheque;
import java15.models.Employee;
import java15.models.MenuItem;
import java15.models.Restaurant;
import java15.repository.ChequeRepository;
import java15.repository.EmployeeRepository;
import java15.repository.MenuItemRepository;
import java15.repository.RestaurantRepository;
import java15.service.ChequeService;
import java15.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final ChequeRepository chequeRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ChequeResponse> getAllCheques() {
        return chequeRepository.findAll()
                .stream()
                .map(cheque -> ChequeResponse.builder()
                        .id(cheque.getId())
                        .menuItems(cheque.getMenuItem().stream()
                                .map(menuItem -> new MenuItemResponse(
                                        menuItem.getId(),
                                        menuItem.getName(),
                                        menuItem.getDescription(),
                                        menuItem.getPrice(),
                                        menuItem.getImageUrl(),
                                        menuItem.isAvailable()))
                                .collect(Collectors.toList()))
                        .priceAverage(cheque.getPriceAverage())
                        .createdAt(cheque.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ChequeResponse getChequeById(Long id) {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cheque not found for ID: " + id));
        return mapToChequeResponse(cheque);
    }
    @Override
    public ChequeResponse createCheque(ChequeRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        List<MenuItem> menuItems = menuItemRepository.findAllById(request.getMenuItemIds());
        Cheque cheque = new Cheque();
        cheque.setEmployee(employee);
        cheque.setMenuItem(menuItems);
        chequeRepository.save(cheque);
        return  ChequeResponse.builder()
                .id(cheque.getId())
                .createdAt(cheque.getCreatedAt())
                .priceAverage(cheque.getPriceAverage())
                .build();
    }

    @Override
    public ChequeResponse updateCheque(Long id, ChequeRequest request) {
        return null;
    }

    @Override
    public void deleteCheque(Long id) {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cheque not found"));
        chequeRepository.delete(cheque);
    }

    @Override
    public String priceAverage() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if (restaurants.isEmpty()) {
            log.error("No restaurant found");
            return null;
        }
        double averagePrice = restaurants.stream()
                .flatMap(restaurant -> restaurant.getMenu().stream())
                .flatMap(restaurant -> restaurant.getCheques().stream())
                .mapToDouble(Cheque::getPriceAverage)
                .average()
                .orElse(0.0);
        return "Средняя цена: " + averagePrice + " сом.";

    }
    private ChequeResponse mapToChequeResponse(Cheque cheque) {

        return ChequeResponse.builder()
                .id(cheque.getId())
                .restaurantId(cheque.getEmployee().getRestaurant().getId())
                .employeeId(cheque.getEmployee().getId())
                .createdAt(cheque.getCreatedAt())
                .priceAverage(cheque.getPriceAverage())
                .build();
    }
}
