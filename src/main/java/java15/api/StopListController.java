package java15.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java15.dto.request.StopListRequest;
import java15.dto.response.SimpleResponse;
import java15.dto.response.StopListResponse;
import java15.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stop-list")
@Slf4j
@RequiredArgsConstructor
public class StopListController {

    private final StopListService stopListService;

    @Secured({"ADMIN", "CHEF", "WAITER"})
    @PostMapping
    public ResponseEntity<StopListResponse> create(@RequestBody @Valid StopListRequest stopListRequest) {
        StopListResponse stopListResponse = stopListService.create(stopListRequest);
        return ResponseEntity.status(201).body(stopListResponse);
    }
    @Secured({"ADMIN", "CHEF", "WAITER"})
    @GetMapping("/{id}")
    public ResponseEntity<StopListResponse>  findById(@PathVariable Long id) {
        return ResponseEntity.ok(stopListService.findById(id));
    }
    @Secured("ADMIN")
    @GetMapping
    public ResponseEntity<List<StopListResponse>> findAll() {
        return ResponseEntity.ok(stopListService.findAll());
    }
    @Secured("ADMIN")
    @Operation(summary = "updated'd")
    @PutMapping("/{id}")
    public ResponseEntity<StopListResponse> update(@PathVariable Long id, @RequestBody @Valid StopListRequest stopListRequest) {
        StopListResponse updatedResponse = stopListService.update(id, stopListRequest);
        return ResponseEntity.ok(updatedResponse);
    }
    @Secured({"ADMIN", "CHEF"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stopListService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
