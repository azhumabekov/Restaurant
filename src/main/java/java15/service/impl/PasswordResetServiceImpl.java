package java15.service.impl;

import java15.models.Employee;
import java15.models.PasswordResetToken;
import java15.repo.EmployeeRepository;
import java15.repo.PasswordResetTokenRepository;
import java15.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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
        // Проверяем, есть ли уже токен для этого пользователя
        PasswordResetToken existingToken = tokenRepository.findByEmployee(employee);
        if (existingToken != null) {
            tokenRepository.delete(existingToken);  // Удаляем старый токен, если он существует
        }

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmployee(employee);
        resetToken.setExpirationTime(LocalDateTime.now().plusMinutes(15));
        tokenRepository.save(resetToken);

        // Здесь вы можете отправить email с токеном
        // Например: emailService.send(email, "Password reset token", "Your token is: " + token);

        return token;
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (resetToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token has expired");
        }

        Employee employee = resetToken.getEmployee();
        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepository.save(employee);

        tokenRepository.delete(resetToken); // Удаляем использованный токен
    }
}