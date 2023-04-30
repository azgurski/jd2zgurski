package com.azgurski.repository;

import com.azgurski.domain.Subscription;

public interface SubscriptionRepository extends CRUDRepository<Long, Subscription> {
    Subscription create(Subscription subscription);
    Subscription findOne(Long subscription_id);
    Subscription update(Subscription subscription);
    Long delete(Long subscription_id);
}
