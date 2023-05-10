package com.azgurski.repository.impl;

import com.azgurski.controller.exceptions.BillingDataNotFoundException;
import com.azgurski.domain.BillingData;
import com.azgurski.repository.BillingDataRepository;
import com.azgurski.rowmapper.BillingDataRowMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Order(1)
@RequiredArgsConstructor
public class BillingDataRepositoryImpl implements BillingDataRepository {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final BillingDataRowMapper billingDataRowMapper;

    //JDBC Template
//    @Override
//    public BillingData create(BillingData billingData) {
//        SqlParameterSource billingDataParameters = new MapSqlParameterSource()
//                .addValue("restaurant_id", billingData.getRestaurant_id())
//                .addValue("iban", billingData.getIban())
//                .addValue("bic", billingData.getBic())
//                .addValue("role_id", billingData.getRole_id());
//
//        namedParameterJdbcTemplate.update(
//                "insert into billing_data values (default, :restaurant_id, :iban, :bic, NOW(), NOW(), default, :role_id)",
//                billingDataParameters);
//
//        List<BillingData> listOne = jdbcTemplate.query("select * from billing_data order by billing_data_id desc", billingDataRowMapper);
//
//        return listOne.stream().findAny().orElse(null);
////        return findOne(listOne.get(0).getBilling_data_id());
//    }

    //REST create
    @Override
    public BillingData create(BillingData billingData) {
        SqlParameterSource billingDataParameters = new MapSqlParameterSource()
                .addValue("restaurant_id", billingData.getRestaurant_id())
                .addValue("iban", billingData.getIban())
                .addValue("bic", billingData.getBic())
                .addValue("role_id", billingData.getRole_id());

        namedParameterJdbcTemplate.update(
                "insert into billing_data values (default, :restaurant_id, :iban, :bic, NOW(), NOW(), default, :role_id)",
                billingDataParameters);

        List<BillingData> listOne = jdbcTemplate.query("select * from billing_data order by billing_data_id desc", billingDataRowMapper);

        return listOne.stream().findAny().orElse(null);
    }

    public Integer getLastId() {
        Integer lastId = jdbcTemplate.update("select max(billing_data_id) from billing_data");
        return lastId;
    }


    @Override
    public BillingData findOne(Long billing_data_id) {
            return jdbcTemplate.queryForObject("select * from billing_data " +
                            "where billing_data_id = " + billing_data_id,
                    new BeanPropertyRowMapper<>(BillingData.class));
    }

//    public BillingData findOne(Long billing_data_id) {
//        try {
//            return jdbcTemplate.queryForObject("select * from billing_data " +
//                            "where billing_data_id = " + billing_data_id,
//                    new BeanPropertyRowMapper<>(BillingData.class));
//        } catch (EmptyResultDataAccessException | NumberFormatException ex) {
//            throw new BillingDataNotFoundException("Billing data with demanded id were not found.");
//        }
//    }
//
//    public BillingData findOneRestaurantId(Long restaurant_id) {
//        return jdbcTemplate.query("select * from billing_data where restaurant_id = ?", new Object[]{restaurant_id},
//                new BeanPropertyRowMapper<>(BillingData.class)).stream().findAny().orElse(null);
//    }

    @Override
    public List<BillingData> findAll() {
        return jdbcTemplate.query("select * from billing_data order by billing_data_id desc",
                new BeanPropertyRowMapper<>(BillingData.class));
    }

    @Override
    public BillingData update(BillingData billingData) {
        SqlParameterSource billingDataParameters = new MapSqlParameterSource()
                .addValue("billing_data_id", billingData.getBilling_data_id())
                .addValue("restaurant_id", billingData.getRestaurant_id())
                .addValue("iban", billingData.getIban())
                .addValue("bic", billingData.getBic())
                .addValue("role_id", billingData.getRole_id());

        namedParameterJdbcTemplate.update("update billing_data set " +
                "restaurant_id=:restaurant_id, " +
                "iban=:iban, " +
                "bic=:bic, " +
                "role_id=:role_id " +
                "where billing_data_id=:billing_data_id", billingDataParameters);

        BillingData updatedBillingData = findOne(billingData.getBilling_data_id());

        return updatedBillingData;
    }

    @Override
    public Long delete(Long billing_data_id) {
        return null;
    }
}