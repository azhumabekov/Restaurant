package java15.api;

import java15.dto.request.StopListRequest;
import java15.dto.response.SimpleResponse;
import java15.dto.response.StopListResponse;
import java15.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stop-list")
@Slf4j
@RequiredArgsConstructor
public class StopListController {

    private final StopListService stopListService;

    @PostMapping
    public StopListResponse create(@RequestBody StopListRequest stopListRequest) {
        return stopListService.create(stopListRequest);
    }

    @GetMapping("/{id}")
    public StopListResponse findById(@PathVariable Long id) {
        return stopListService.findById(id);
    }

    @GetMapping
    public List<StopListResponse> findAll() {
        return stopListService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody StopListRequest stopListRequest) {
        stopListService.update(id, stopListRequest);
        return ResponseEntity.ok("Stop list updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        stopListService.delete(id);
        return ResponseEntity.ok("Stop list deleted successfully");
    }
}
