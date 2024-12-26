package java15.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "Token cannot be blank")
    private String token;
    @NotBlank(message = "New password cannot be blank")
    @Size(min = 6,max = 20, message = "Password must be between 6 and 20 characters")
    private String newPassword;
}
