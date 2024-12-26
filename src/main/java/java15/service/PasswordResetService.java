package java15.service;

import java15.dto.request.ResetPasswordRequest;

public interface PasswordResetService {
    String generateResetToken(String email);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);
}
