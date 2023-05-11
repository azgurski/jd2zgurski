package com.zgurski.repository.rowmapper;

import com.zgurski.domain.Subscription;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zgurski.repository.columns.SubscriptionColumns.DAYS_QUANTITY;
import static com.zgurski.repository.columns.SubscriptionColumns.IS_PAID;
import static com.zgurski.repository.columns.SubscriptionColumns.NEXT_PAYMENT_DATE;
import static com.zgurski.repository.columns.SubscriptionColumns.RESTAURANT_ID;
import static com.zgurski.repository.columns.SubscriptionColumns.ROLE_ID;
import static com.zgurski.repository.columns.SubscriptionColumns.SUBSCRIPTION_ID;

@Component
public class SubscriptionRowMapper implements RowMapper<Subscription> {
    @Override
    public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subscription subscription;


        subscription = Subscription.builder()
                .subscription_id(rs.getLong(SUBSCRIPTION_ID))
                .restaurant_id(rs.getLong(RESTAURANT_ID))
                .is_paid(rs.getBoolean(IS_PAID))
                .days_quantity(rs.getLong(DAYS_QUANTITY))
                .next_payment_date(rs.getDate(NEXT_PAYMENT_DATE))
                .role_id(rs.getLong(ROLE_ID))
                .build();


        return subscription;
    }
}