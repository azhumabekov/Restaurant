package java15.service;

import java15.dto.request.ChangePasswordRequest;
import java15.dto.request.RegistrationRequest;

public interface EmployeeService {
    void registerUser(RegistrationRequest request);

    void changePassword(ChangePasswordRequest request);

//    void updateEmployeeStatus(Long id, boolean isActive);
}
