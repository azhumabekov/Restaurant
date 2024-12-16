package java15.service;

import java15.dto.request.AuthRequest;
import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;
import java15.dto.response.AuthResponse;

public interface EmployeeService {
    void registerUser(RegistrationRequest request);

    void changePassword(ChangePasswordRequest request);

    AuthResponse login(AuthRequest request);

//    void updateEmployeeStatus(Long id, boolean isActive);
}
