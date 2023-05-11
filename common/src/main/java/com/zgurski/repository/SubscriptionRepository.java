package com.zgurski.repository;

import com.zgurski.domain.Subscription;

import java.util.List;

public interface SubscriptionRepository extends CRUDRepository<Long, Subscription> {
    Subscription findOne(Long subscription_id);

    Subscription update(Subscription subscription);

    List<Subscription> searchUnpaidSubscriptions();
}
