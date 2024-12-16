package java15.service.impl;

import java15.dto.request.RegistrationRequest;
import java15.models.Employee;
import java15.repo.EmployeeRepository;
import java15.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest request) {
        employeeRepository.findUserByEmailEqualsIgnoreCase(request.getEmail());


        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setRole(request.getRole());
        employee.setExperience(request.getExperience());
        employeeRepository.save(employee);
    }
}
