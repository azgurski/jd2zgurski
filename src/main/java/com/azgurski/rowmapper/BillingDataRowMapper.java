package com.azgurski.rowmapper;

import com.azgurski.domain.BillingData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.azgurski.repository.columns.BillingDataColumns.BIC;
import static com.azgurski.repository.columns.BillingDataColumns.BILLING_DATA_ID;
import static com.azgurski.repository.columns.BillingDataColumns.IBAN;
import static com.azgurski.repository.columns.BillingDataColumns.RESTAURANT_ID;
import static com.azgurski.repository.columns.BillingDataColumns.ROLE_ID;

@Component
public class BillingDataRowMapper implements RowMapper<BillingData> {

    @Override
    public BillingData mapRow(ResultSet rs, int rowNum) throws SQLException {
        BillingData billingData;

        billingData = BillingData.builder()
                .billing_data_id(rs.getLong(BILLING_DATA_ID))
                .restaurant_id(rs.getLong(RESTAURANT_ID))
                .iban(rs.getString(IBAN))
                .bic(rs.getString(BIC))
                .role_id(rs.getLong(ROLE_ID))
                .build();

        return billingData;
    }
}