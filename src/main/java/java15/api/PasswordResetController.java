package java15.api;

import java15.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    //    @PostMapping("/request-reset")
//    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
//        String token = passwordResetService.generateResetToken(email);
//        // Возвращаем токен (в реальном приложении токен отправляется по email)
//        return ResponseEntity.ok("Password reset token: " + token);
//    }
    @PostMapping("/request-reset")
    public ResponseEntity<String> requestPasswordReset(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required.");
        }
        String token = passwordResetService.generateResetToken(email);
        return ResponseEntity.ok("Password reset token: " + token);
    }

//    @PostMapping("/reset")
//    public ResponseEntity<String> resetPassword(@RequestParam ResetPasswordRequest body) {
//        passwordResetService.resetPassword(body.getNewPassword(), body.getToken());
//        return ResponseEntity.ok("Password has been reset successfully");
//    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword");

        if (token == null || newPassword == null) {
            return ResponseEntity.badRequest().body("Token and newPassword are required");
        }

        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password has been reset successfully");
    }
}