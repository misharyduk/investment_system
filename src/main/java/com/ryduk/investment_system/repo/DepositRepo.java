package com.ryduk.investment_system.repo;

import com.ryduk.investment_system.model.Deposit;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositRepo extends JpaRepository<Deposit, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE deposit SET is_taken=TRUE WHERE id=:id", nativeQuery = true)
    void confirmInvestment(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE deposit SET is_taken=FALSE WHERE id=:id", nativeQuery = true)
    void releaseInvestment(@Param("id") Integer id);

    Page<Deposit> findByIsTakenFalse(Pageable pageable);
    long countByIsTakenFalse();
}
