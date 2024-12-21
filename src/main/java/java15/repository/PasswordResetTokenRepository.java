package java15.repository;// src/main/java/com/restaurant/repositories/PasswordResetTokenRepository.java

import java15.models.Employee;
import java15.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    PasswordResetToken findByEmployee(Employee employee);

}