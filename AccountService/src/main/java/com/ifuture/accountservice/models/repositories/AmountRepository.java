package com.ifuture.accountservice.models.repositories;

import com.ifuture.accountservice.models.Amount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountRepository extends JpaRepository<Amount, Integer> {
}
