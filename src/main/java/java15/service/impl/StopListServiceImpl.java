package java15.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java15.dto.request.StopListRequest;
import java15.dto.response.StopListResponse;
import java15.models.StopList;
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

    @Override
    public StopListResponse create(StopListRequest stopListRequest) {
        log.info("Creating stop list with reason: {}", stopListRequest.getReason());

        if (stopListRequest == null || stopListRequest.getReason() == null || stopListRequest.getReason().isEmpty()) {
            log.error("Invalid StopListRequest: reason is empty or null");
            throw new IllegalArgumentException("Reason cannot be null or empty");
        }

        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.getReason());
        stopListRepository.save(stopList);

        log.info("Created stop list: {}", stopList);

        return StopListResponse.builder()
                .id(stopList.getId())
                .reason(stopList.getReason())
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
    public void update(Long id, StopListRequest stopListRequest) {
        log.info("Updating stop list with id {}", id);

        if (!stopListRepository.existsById(id)) {
            log.error("StopList not found with id {}", id);
            throw new EntityNotFoundException("StopList not found with id " + id);
        }

        StopList stopList = stopListRepository.findById(id).get();
        stopList.setReason(stopListRequest.getReason());
        stopListRepository.save(stopList);

        log.info("Updated stop list: {}", stopList);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting stop list with id {}", id);

        if (!stopListRepository.existsById(id)) {
            log.error("StopList not found with id {}", id);
            throw new EntityNotFoundException("StopList not found with id " + id);
        }

        stopListRepository.deleteById(id);
        log.info("Deleted stop list with id {}", id);
    }
}