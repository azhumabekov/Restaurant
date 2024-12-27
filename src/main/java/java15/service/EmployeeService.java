package java15.service;

import java15.dto.request.AuthRequest;
import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.dto.response.AuthResponse;
import java15.dto.response.EmployeeResponse;
import java15.enums.Role;
import java15.models.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse registerUser(RegistrationRequest request);

    void approveEmployeeRole(Long employeeId, Role newRole);

    Employee addEmployee(String firstName, String lastName, Long restaurantId);

    void changePassword(ChangePasswordRequest request);

    AuthResponse login(AuthRequest request);

    List<EmployeeResponse> findAll();

    void removeEmployee(Long employeeId);

    EmployeeResponse getEmployeeById(Long employeeId);

//    void updateEmployeeStatus(Long id, boolean isActive);
}
