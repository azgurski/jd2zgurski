package com.zgurski.service;

import com.zgurski.domain.BillingData;
import com.zgurski.repository.BillingDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingDataServiceImpl implements BillingDataService{
    private final BillingDataRepository billingDataRepository;

    @Override
    public List<BillingData> findAll() {
        return billingDataRepository.findAll();
    }

    @Override
    public BillingData findOne(Long billing_data_id) {
            return billingDataRepository.findOne(billing_data_id);
    }

    @Override
    public BillingData update(BillingData billingData) {
        return billingDataRepository.update(billingData);
    }

    @Override
    public BillingData create(BillingData billingData) {
        return billingDataRepository.create(billingData);
    }

    @Override
    public Integer getLastId() {
        return billingDataRepository.getLastId();
    }
}
