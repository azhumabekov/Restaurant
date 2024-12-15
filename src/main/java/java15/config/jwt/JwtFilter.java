package java15.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java15.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // "Bearer "
        String headerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (headerToken != null && headerToken.startsWith(BEARER_PREFIX)) {
            headerToken = headerToken.substring(BEARER_PREFIX.length());
            try {
                Employee employee = jwtService.verifyToken(headerToken);
                if (employee != null) {
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(
                                            employee.getUsername(),
                                            null,
                                            employee.getAuthorities()));
                }

            } catch (JWTVerificationException e) {
                System.err.println(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);

    }
}
