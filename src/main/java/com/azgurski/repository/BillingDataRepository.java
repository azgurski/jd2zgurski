package com.azgurski.repository;

import com.azgurski.domain.BillingData;

import java.util.Optional;

public interface BillingDataRepository extends CRUDRepository<Long, BillingData> {
    BillingData create(BillingData billingData);

    BillingData findOne(Long billing_data_id);
    BillingData update(BillingData billingData);
    Long delete(Long billing_data_id);

    Integer getLastId();
}

