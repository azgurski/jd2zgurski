package com.azgurski.repository.impl;

import com.azgurski.domain.Subscription;
import com.azgurski.repository.SubscriptionRepository;
import com.azgurski.rowmapper.SubscriptionRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@Order(1)
@RequiredArgsConstructor
public class SubscriptionRepositoryJdbcTemplateImpl implements SubscriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SubscriptionRowMapper subscriptionRowMapper;



    @Override
    public Subscription create(Subscription subscription) {
        return null;
    }

    @Override
    public Subscription findOne(Long subscription_id) {
        return jdbcTemplate.queryForObject("select * from subscriptions " +
                "where subscription_id = ?" + subscription_id, new BeanPropertyRowMapper<>(Subscription.class));

//        return jdbcTemplate.queryForObject("select * from subscriptions " +
//                "where subscription_id = " + subscription_id, subscriptionRowMapper);
    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }

    @Override
    public Subscription update(Subscription subscription) {
        return null;
    }

    @Override
    public Long delete(Long subscription_id) {
        return null;
    }
}
