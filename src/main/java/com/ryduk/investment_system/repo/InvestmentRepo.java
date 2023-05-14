package com.ryduk.investment_system.repo;

import com.ryduk.investment_system.model.Investment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepo extends JpaRepository<Investment, Integer> {
    // TODO: write query
//    Page<Investment> findAllByUserId(Integer clientId, Pageable pageable);
}
