package java15.service.impl;

import java15.dto.request.ResetPasswordRequest;
import java15.models.Employee;
import java15.models.PasswordResetToken;
import java15.repository.EmployeeRepository;
import java15.repository.PasswordResetTokenRepository;
import java15.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final EmployeeRepository employeeRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String generateResetToken(String email) {
        Employee employee = (Employee) employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found with email: " + email);
        }
        PasswordResetToken existingToken = tokenRepository.findByEmployee(employee);
        if (existingToken != null) {
            tokenRepository.delete(existingToken);
        }

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmployee(employee);
        resetToken.setExpirationTime(LocalDateTime.now().plusMinutes(15));
        tokenRepository.save(resetToken);
        return token;
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        PasswordResetToken resetToken = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (resetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token has expired");
        }

        Employee employee = resetToken.getEmployee();
        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
        employeeRepository.save(employee);

        tokenRepository.delete(resetToken);
    }

}