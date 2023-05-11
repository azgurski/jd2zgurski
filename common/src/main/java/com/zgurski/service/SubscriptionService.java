package com.zgurski.service;

import com.zgurski.domain.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription findOne(Long subscription_id);

    Subscription update(Subscription subscription);

    List<Subscription> searchUnpaidSubscriptions();
}
