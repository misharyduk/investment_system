package com.ryduk.investment_system.security;

import com.ryduk.investment_system.model.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UserDetailsImpl getUser(){
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
