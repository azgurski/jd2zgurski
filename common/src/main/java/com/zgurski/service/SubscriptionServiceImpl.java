package com.zgurski.service;

import com.zgurski.domain.Subscription;
import com.zgurski.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription update(Subscription subscription) {
        return subscriptionRepository.update(subscription);
    }

    @Override
    public List<Subscription> searchUnpaidSubscriptions() {
        return subscriptionRepository.searchUnpaidSubscriptions();
    }

    @Override
    public Subscription findOne(Long subscription_id) {
        return subscriptionRepository.findOne(subscription_id);
    }
}