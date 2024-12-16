package java15.dto.request;

import java15.enums.Role;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Role role;
    private int experience;
    private Long restaurantId;
}
