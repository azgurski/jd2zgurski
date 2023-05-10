package com.azgurski.repository;

import com.azgurski.domain.Subscription;

import java.util.List;

public interface SubscriptionRepository extends CRUDRepository<Long, Subscription> {
    Subscription findOne(Long subscription_id);

    Subscription update(Subscription subscription);

    List<Subscription> searchUnpaidSubscriptions();
}
