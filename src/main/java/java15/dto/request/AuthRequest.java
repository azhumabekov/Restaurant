package java15.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotNull(message = "email cannot be null") @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6,max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
}
