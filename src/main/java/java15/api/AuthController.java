package java15.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java15.dto.request.AuthRequest;
import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.dto.response.AuthResponse;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication and user management")
@RequiredArgsConstructor
public class AuthController {
    private final EmployeeService employeeService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return a success message")

    public AuthResponse login(@RequestBody AuthRequest request) {
        return employeeService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        employeeService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        employeeService.changePassword(request);
        return ResponseEntity.ok("Password changed successfully!");
    }

//    @PatchMapping("/{id}/status")
//    public ResponseEntity<String> updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean isActive) {
//        employeeService.updateEmployeeStatus(id, isActive);
//        return ResponseEntity.ok("Employee status updated successfully!");
//    }

}

