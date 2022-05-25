package com.ifuture.accountservice.services;

import com.ifuture.accountservice.models.Amount;
import com.ifuture.accountservice.models.repositories.AmountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AmountRepository amountRepository;

    private final AtomicLong getAmountRequestsCount = new AtomicLong();
    private final AtomicLong addAmountRequestsCount = new AtomicLong();
    private final AtomicLong getAmountRequestsTotal = new AtomicLong();
    private final AtomicLong addAmountRequestsTotal = new AtomicLong();

    @Override
    public Long getAmount(Integer id) {
        getAmountRequestsCount.incrementAndGet();
        getAmountRequestsTotal.incrementAndGet();
        Optional<Amount> amountOptional = amountRepository.findById(id);
        return amountOptional.isPresent() ? amountOptional.get().getValue() : 0L;
    }

    @Transactional
    @Override
    public void addAmount(Integer id, Long value) {
        addAmountRequestsCount.incrementAndGet();
        addAmountRequestsTotal.incrementAndGet();
        Amount amount = amountRepository.findById(id).orElse(new Amount(id, 0L));
        amount.setValue(amount.getValue() + value);
        amountRepository.save(amount);
    }

    @Override
    public void resetStats() {
        getAmountRequestsTotal.getAndSet(0);
        addAmountRequestsTotal.getAndSet(0);
        getAmountRequestsCount.getAndSet(0);
        addAmountRequestsCount.getAndSet(0);
    }

    @Scheduled(fixedRate = 1000)
    public void printTPS() {
        log.debug("Stats:");
        log.debug("\tgetAmount {} tps, {} total", getAmountRequestsCount.getAndSet(0), getAmountRequestsTotal.get());
        log.debug("\taddAmount {} tps, {} total", addAmountRequestsCount.getAndSet(0), addAmountRequestsTotal.get());
    }
}
