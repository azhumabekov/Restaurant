package java15.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import java15.dto.request.ChequeRequest;
import java15.dto.response.ChequeResponse;
import java15.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cheques")
@RequiredArgsConstructor
@Tag(name = "cheque")
public class ChequeController {

    private final ChequeService chequeService;

    @GetMapping
    public List<ChequeResponse> getAllCheques() {
        return chequeService.getAllCheques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChequeResponse> getChequeById(@PathVariable Long id) {
        return ResponseEntity.ok(chequeService.getChequeById(id));
    }

    @PostMapping
    public ResponseEntity<ChequeResponse> createCheque(@RequestBody ChequeRequest request) {
        return ResponseEntity.status(201).body(chequeService.createCheque(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChequeResponse> updateCheque(@PathVariable Long id, @RequestBody ChequeRequest request) {
        return ResponseEntity.ok(chequeService.updateCheque(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheque(@PathVariable Long id) {
        chequeService.deleteCheque(id);
        return ResponseEntity.noContent().build();
    }
}