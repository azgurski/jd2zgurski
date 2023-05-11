package com.zgurski.repository;

import com.zgurski.domain.BillingData;

public interface BillingDataRepository extends CRUDRepository<Long, BillingData> {
    BillingData create(BillingData billingData);

    BillingData findOne(Long billing_data_id);
    BillingData update(BillingData billingData);
    Long delete(Long billing_data_id);

    Integer getLastId();
}

