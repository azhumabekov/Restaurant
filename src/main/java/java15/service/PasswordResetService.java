package java15.service;

public interface PasswordResetService {
    String generateResetToken(String email);

    void resetPassword(String token, String newPassword);
}
