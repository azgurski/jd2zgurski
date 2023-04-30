package com.azgurski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;

//Hibernate
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Subscription {

    private Long subscription_id;
    private Long restaurant_id;
    private Boolean is_paid;
    private Long days_quantity;
    private Date next_payment_date;
    private Long role_id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}