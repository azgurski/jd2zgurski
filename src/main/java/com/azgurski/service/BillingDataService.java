package com.azgurski.service;

import com.azgurski.domain.BillingData;

import java.util.List;

public interface BillingDataService {
    BillingData findOne(Long billing_data_id);

    BillingData create(BillingData billingData);

    BillingData update(BillingData billingData);

    Integer getLastId();

    List<BillingData> findAll();
}
