package java15.service.impl;

import java15.dto.request.ChequeRequest;
import java15.dto.response.ChequeResponse;
import java15.models.Cheque;
import java15.models.Restaurant;
import java15.repository.RestaurantRepository;
import java15.service.ChequeService;
import java15.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {

    private final RestaurantRepository restaurantRepository;


    @Override
    public List<ChequeResponse> getAllCheques() {
        return List.of();
    }

    @Override
    public ChequeResponse getChequeById(Long id) {
        return null;
    }

    @Override
    public ChequeResponse createCheque(ChequeRequest request) {
        return null;
    }

    @Override
    public ChequeResponse updateCheque(Long id, ChequeRequest request) {
        return null;
    }

    @Override
    public void deleteCheque(Long id) {

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
}
