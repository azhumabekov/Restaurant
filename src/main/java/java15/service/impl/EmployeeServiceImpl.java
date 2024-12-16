package java15.service.impl;

import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.models.Employee;
import java15.models.Restaurant;
import java15.repo.EmployeeRepository;
import java15.repo.RestaurantRepository;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void registerUser(RegistrationRequest request) {
        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with email already exists!");
        }
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Restaurant not found with ID: " + request.getRestaurantId()));

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setRole(request.getRole());
        employee.setExperience(request.getExperience());
        employee.setRestaurant(restaurant);
        employeeRepository.save(employee);
        log.info("User with email {} registered successfully!", request.getEmail());

    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Employee employee = employeeRepository.findUserByEmailEqualsIgnoreCase(request.getEmail());

        if (!passwordEncoder.matches(request.getOldPassword(), employee.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
        employeeRepository.save(employee);
    }


//    @Override
//    public void updateEmployeeStatus(Long id, boolean isActive) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//        employee.setIsActive(isActive);
//        employeeRepository.save(employee);
//    }
}
