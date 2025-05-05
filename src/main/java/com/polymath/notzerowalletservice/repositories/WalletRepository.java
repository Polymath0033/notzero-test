package com.polymath.notzerowalletservice.repositories;

import com.polymath.notzerowalletservice.model.Wallet;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByAccountNumberAndActiveTrue(String accountNumber);
}
