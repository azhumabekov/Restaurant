package java15.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java15.dto.request.StopListRequest;
import java15.dto.response.StopListResponse;
import java15.models.MenuItem;
import java15.models.StopList;
import java15.repository.MenuItemRepository;
import java15.repository.StopListRepository;
import java15.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {

    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public StopListResponse create(StopListRequest stopListRequest) {
        if (stopListRepository.existsById(stopListRequest.getMenuItemId())) {
            throw new RuntimeException("Menu item is already in the stop list");
        }
        log.info("Creating stop list with reason: {}", stopListRequest.getReason());

        MenuItem menuItem = menuItemRepository.findById(stopListRequest.getMenuItemId())
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));
        menuItem.setAvailable(false); //пометить недоступным
        menuItemRepository.save(menuItem);

        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.getReason());
        stopList.setMenuItem(menuItem);
        stopListRepository.save(stopList);

        log.info("Created stop list: {}", stopList);

        return StopListResponse.builder()
                .id(stopList.getId())
                .reason(stopList.getReason())
                .menuItemId(stopList.getMenuItem().getId())
                .build();
    }

    @Override
    public StopListResponse findById(Long id) {
        Optional<StopList> byId = stopListRepository.findById(id);

        if (byId.isPresent()) {
            StopList stopList = byId.get();
            return StopListResponse.builder()
                    .id(stopList.getId())
                    .reason(stopList.getReason())
                    .build();
        } else {
            log.error("StopList not found with id {}", id);
            throw new EntityNotFoundException("StopList not found with id " + id);
        }
    }

    @Override
    public List<StopListResponse> findAll() {
        return stopListRepository.findAll().stream()
                .map(stopList -> StopListResponse.builder()
                        .id(stopList.getId())
                        .reason(stopList.getReason())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public StopListResponse update(Long id, StopListRequest stopListRequest) {
        log.info("Updating stop list with id {}", id);

        if (!stopListRepository.existsById(id)) {
            log.error("StopList not found with id {}", id);
            throw new EntityNotFoundException("StopList not found with id " + id);
        }

        StopList stopList = stopListRepository.findById(id).get();
        stopList.setReason(stopListRequest.getReason());
        stopListRepository.save(stopList);

        log.info("Updated stop list: {}", stopList);
        return null;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting stop list with id {}", id);

        StopList stopList = stopListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StopList not found with id " + id));
        stopListRepository.delete(stopList);

        stopList.getMenuItem().setAvailable(true);
        menuItemRepository.save(stopList.getMenuItem());

        log.info("Deleted stop list with id {}", id);
    }
}