package java15.config.security;// src/main/java/com/restaurant/security/CustomUserDetailsService.java


import java15.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findUserByEmailEqualsIgnoreCase(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}