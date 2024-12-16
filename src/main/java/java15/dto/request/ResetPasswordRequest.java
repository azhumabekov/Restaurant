package java15.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordRequest {
    private String token;
    private String newPassword;
}