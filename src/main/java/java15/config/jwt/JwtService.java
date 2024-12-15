package java15.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java15.dto.response.JwtTokenResponse;
import java15.models.Employee;
import java15.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${app.security.jwt.secret_key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration}")
    private Long expiration;

    private final EmployeeRepository employeeRepository;

    public JwtTokenResponse createToken(Employee employee) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        String token = JWT.create()
                .withClaim("email", employee.getEmail())
                .withClaim("firstName", employee.getFirstName())
                .withClaim("lastName", employee.getLastName())
                .withClaim("dateOfBirth", employee.getDateOfBirth().toString())
                .withClaim("id", employee.getId())
                .withClaim("experience", employee.getExperience())
                .withClaim("phoneNumber", employee.getPhoneNumber())
                .withClaim("role", employee.getRole().name())
                .withIssuedAt(zonedDateTime.toInstant())
                .withExpiresAt(zonedDateTime.plusSeconds(expiration).toInstant())
                .sign(getAlgorithm());

        return new JwtTokenResponse(token, zonedDateTime, zonedDateTime.plusSeconds(expiration));
    }

    public Employee verifyToken(String token) {
        try {
            Algorithm algorithm = getAlgorithm();
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            String email = verify.getClaim("email").asString();
            Employee employee = employeeRepository.findUserByEmailEqualsIgnoreCase(email);
            if (employee == null) {
                logger.warn("User not found with email: " + email);
            }
            return employee;
        } catch (Exception e) {
            logger.error("Token verification failed: ", e);
            return null;
        }
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC512(secretKey);
    }
}