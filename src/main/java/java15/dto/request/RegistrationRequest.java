package java15.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RegistrationRequest {
    @NotBlank(message = "First name is required")
    @Size(max = 15, message = "First name cannot exceed 50 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(max = 15, message = "Last name cannot exceed 50 characters")
    private String lastName;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format, must contain: \"@gmail.com\"")
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    @NotNull(message = "Phone number cannot be null")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Min(value = 0, message = "Experience cannot be negative")
    private int experience;

    @NotNull(message = "Restaurant ID cannot be null")
    private Long restaurantId;
}
