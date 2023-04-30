package com.azgurski.service;

import com.azgurski.domain.Subscription;
import com.azgurski.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription findOne(Long subscription_id) {
        return subscriptionRepository.findOne(subscription_id);
    }
}
