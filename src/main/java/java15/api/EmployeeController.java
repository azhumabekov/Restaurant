package java15.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import java15.dto.response.EmployeeResponse;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Secured("ADMIN")
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.findAll();

    }

}
