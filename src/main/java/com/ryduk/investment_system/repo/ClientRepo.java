package com.ryduk.investment_system.repo;

import com.ryduk.investment_system.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findByUsername(String username);
}
