package com.ryduk.investment_system.service;

import com.ryduk.investment_system.model.Client;
import com.ryduk.investment_system.model.UserDetailsImpl;
import com.ryduk.investment_system.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("qweqasd");
        Client client = clientRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new UserDetailsImpl(client);
    }
}
