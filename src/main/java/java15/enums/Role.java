package java15.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,  WAITER,
        CHEF,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
