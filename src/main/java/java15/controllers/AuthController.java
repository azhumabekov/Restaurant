package java15.controllers;


import jakarta.validation.Valid;
import java15.config.jwt.JwtService;
import java15.dto.request.AuthRequest;
import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.dto.response.AuthResponse;
import java15.dto.response.JwtTokenResponse;
import java15.models.Employee;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
                            /// Manager ///
    private final AuthenticationProvider authenticationManager;
    private final EmployeeService employeeService;
    private final JwtService jwtService;

    @PostMapping("/login")
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

