package java15.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Current password cannot be null")
    @Size(min = 6, max = 20, message = "Current password must be between 6 and 20 characters")
    private String currentPassword;
    @NotNull(message = "New password cannot be null")
    private String newPassword;
}
