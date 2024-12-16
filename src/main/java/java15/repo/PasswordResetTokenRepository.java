package java15.repo;// src/main/java/com/restaurant/repositories/PasswordResetTokenRepository.java

import java15.models.Employee;
import java15.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    PasswordResetToken findByEmployee(Employee employee);
}