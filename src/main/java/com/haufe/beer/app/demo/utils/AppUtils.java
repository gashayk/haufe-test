package com.haufe.beer.app.demo.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

public class AppUtils {

    private AppUtils() {
    }

    public static Collection<GrantedAuthority> getGrantedAuthorities(Principal principal) {
        return ((UsernamePasswordAuthenticationToken) principal).getAuthorities();
    }

    public static boolean isAdmin(Collection<GrantedAuthority> authorities) {
        return authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public static boolean isManufacturer(Collection<GrantedAuthority> authorities) {
        return authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_MANUFACTURER"));
    }
}
