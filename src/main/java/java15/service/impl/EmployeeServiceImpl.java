package java15.service.impl;

import java15.config.jwt.JwtService;
import java15.dto.request.AuthRequest;
import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.dto.response.AuthResponse;
import java15.dto.response.EmployeeResponse;
import java15.enums.Role;
import java15.exceptions.InvalidPasswordException;
import java15.models.Employee;
import java15.models.Restaurant;
import java15.repository.EmployeeRepository;
import java15.repository.RestaurantRepository;
import java15.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;

    @Override
    public EmployeeResponse registerUser(RegistrationRequest request) {
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
        employee.setExperience(request.getExperience());
        employee.setRole(Role.USER);
        employee.setRestaurant(restaurant);
        employeeRepository.save(employee);

        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() + 1);
        restaurantRepository.save(restaurant);
        log.info("User with email {} registered successfully!", request.getEmail());
        return null;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setRestaurant(restaurant);

        return employeeRepository.save(employee);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Employee employee = employeeRepository.findUserByEmailEqualsIgnoreCase(request.getEmail());

        if (!passwordEncoder.matches(request.getCurrentPassword(), employee.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
        employeeRepository.save(employee);
    }

//    @Override
//    public AuthResponse login(AuthRequest request) {
//        Authentication authentication = authenticationProvider.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Employee employee = (Employee) authentication.getPrincipal();
//
//        return AuthResponse.builder()
//                .jwtTokenResponse(jwtService.createToken(employee))
//                .email(employee.getEmail())
//                .role(employee.getRole())
//                .httpStatus(HttpStatus.OK)
//                .build();
//
//    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Employee employee = (Employee) employeeRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!isPasswordCorrect(request)) {
            throw new InvalidPasswordException("Incorrect password for user " + request.getEmail());
        }
        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return AuthResponse.builder()
                .jwtTokenResponse(jwtService.createToken(employee))
                .email(employee.getEmail())
                .role(employee.getRole())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private boolean isPasswordCorrect(AuthRequest request) {

        return true;
    }
//@Override
//public AuthResponse login(AuthRequest request) {
//    Authentication authentication = authenticationProvider.authenticate(
//            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//    );
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    Employee employee = (Employee) authentication.getPrincipal();
//
//    return AuthResponse.builder()
//            .jwtTokenResponse(jwtService.createToken(employee))
//            .email(employee.getEmail())
//            .role(employee.getRole())
//            .httpStatus(HttpStatus.OK)
//            .build();
//
//}

    @Override
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDateOfBirth(),
                        employee.getEmail(),
                        employee.getPhoneNumber(),
                        String.valueOf(employee.getRole()),
                        employee.getExperience()
                )).collect(Collectors.toList());
    }

    @Override
    public void removeEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        Restaurant restaurant = employee.getRestaurant();

        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees() - 1);
        restaurantRepository.save(restaurant);

        employeeRepository.delete(employee);

        log.info("Employee {} {} removed from restaurant {}", employee.getFirstName(), employee.getLastName(), restaurant.getName());
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        log.info("Get employee by ID {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDateOfBirth(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getRole().toString(),
                employee.getExperience()
        );
    }


//    @Override
//    public void updateEmployeeStatus(Long id, boolean isActive) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//        employee.setIsActive(isActive);
//        employeeRepository.save(employee);
//    }
}
