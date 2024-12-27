package java15.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java15.dto.response.EmployeeResponse;
import java15.enums.Role;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Secured("ADMIN")
    @Operation(summary = "Получить список всех сотрудников", description = "Требуется роль ADMIN")
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> all = employeeService.findAll();
        return ResponseEntity.ok(all);
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        employeeService.removeEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @Secured("ADMIN")
    @PutMapping("/{id}/role")
    public ResponseEntity<String> changeEmployeeRole(@PathVariable Long id, @RequestParam Role newRole) {
        employeeService.approveEmployeeRole(id, newRole);
        return ResponseEntity.ok("Role changed successfully");
    }
}
