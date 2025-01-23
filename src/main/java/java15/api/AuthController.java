package java15.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java15.dto.request.AuthRequest;
import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.dto.request.ResetPasswordRequest;
import java15.dto.response.AuthResponse;
import java15.models.Employee;
import java15.models.PasswordResetToken;
import java15.repository.EmployeeRepository;
import java15.repository.PasswordResetTokenRepository;
import java15.service.EmployeeService;
import java15.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication and user management")
@RequiredArgsConstructor
public class AuthController {
    private final EmployeeService employeeService;
    private final PasswordResetService passwordResetService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmployeeRepository employeeRepository;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "401", description = "Incorrect password")
    })
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        AuthResponse response = employeeService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequest request) {
        employeeService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @Operation(summary = "Change password")
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        employeeService.changePassword(request);
        return ResponseEntity.ok("Password changed successfully!");
    }

    @Operation(summary = "Reset password, end to generated token")
    @PostMapping("/generate-reset-token")
    public ResponseEntity<String> generateResetToken(@RequestParam String email) {
        String token = passwordResetService.generateResetToken(email);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "enter the token and reset password")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        passwordResetService.resetPassword(request);
        return ResponseEntity.ok("Password reset successfully!");
    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));
//
//        if (resetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
//            throw new IllegalArgumentException("Token has expired");
//        }
//
//        Employee user = resetToken.getEmployee();
//        user.setPassword(newPassword); // Не забудьте хэшировать пароль!
//        employeeRepository.save(user);
//
//        return ResponseEntity.ok("Password reset successful");
//    }

//    @PatchMapping("/{id}/status")
//    public ResponseEntity<String> updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean isActive) {
//        employeeService.updateEmployeeStatus(id, isActive);
//        return ResponseEntity.ok("Employee status updated successfully!");
//    }

}

