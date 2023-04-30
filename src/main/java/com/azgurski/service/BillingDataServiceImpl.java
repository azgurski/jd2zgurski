package com.azgurski.service;

import com.azgurski.controller.exceptions.BillingDataNotFoundException;
import com.azgurski.domain.BillingData;
import com.azgurski.repository.BillingDataRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
