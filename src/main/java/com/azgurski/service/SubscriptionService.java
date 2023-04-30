package com.azgurski.service;

import com.azgurski.domain.Subscription;

public interface SubscriptionService {
    Subscription findOne(Long subscription_id);
}
