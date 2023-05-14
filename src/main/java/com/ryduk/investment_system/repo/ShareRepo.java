package com.ryduk.investment_system.repo;

import com.ryduk.investment_system.model.Deposit;
import com.ryduk.investment_system.model.Share;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShareRepo extends JpaRepository<Share, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE share SET is_taken=TRUE WHERE id=:id", nativeQuery = true)
    void confirmInvestment(@Param("id") int id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE share SET is_taken=FALSE WHERE id=:id", nativeQuery = true)
    void releaseInvestment(@Param("id") Integer id);

    Page<Share> findByIsTakenFalse(Pageable pageable);
    long countByIsTakenFalse();
}
