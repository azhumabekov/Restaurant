package java15.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
