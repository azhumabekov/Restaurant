package java15.dto.response;

import java15.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private JwtTokenResponse jwtTokenResponse;
    private String message;
    private HttpStatus httpStatus;
    private String email;
    private Role role;
}