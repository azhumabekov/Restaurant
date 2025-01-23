package java15.service.impl;

import jakarta.annotation.PostConstruct;
import java15.enums.Role;
import java15.models.Employee;
import java15.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initAdmin() {
        String adminEmail = "admin@gmail.com";
        String adminPassword = "admin123";

        if (employeeRepository.findByEmail(adminEmail).isEmpty()) {
            Employee admin = new Employee();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setRole(Role.ADMIN);
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setDateOfBirth(LocalDate.of(1990, 1, 1));
            admin.setExperience(0);
            admin.setPhoneNumber("0000000000");

            employeeRepository.save(admin);
            System.out.println("Admin user created with email: " + adminEmail);
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}