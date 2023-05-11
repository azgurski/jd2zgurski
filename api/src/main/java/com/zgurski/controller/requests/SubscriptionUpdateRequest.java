package com.zgurski.controller.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubscriptionUpdateRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long subscription_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long restaurant_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean is_paid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long days_quantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date next_payment_date;

    private Long role_id;
}
