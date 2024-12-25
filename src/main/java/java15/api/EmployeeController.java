package java15.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import java15.dto.response.EmployeeResponse;
import java15.models.Employee;
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
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> all = employeeService.findAll();
        return ResponseEntity.ok(all);
    }

    @Secured("ADMIN")
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Long id) {
        employeeService.removeEmployee(id);
        return ResponseEntity.ok("Employee deleted");
    }
}
