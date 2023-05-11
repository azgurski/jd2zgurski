package com.zgurski.controller.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BillingDataUpdateRequest {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long restaurant_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^(?:(?:IT|SM)\\d{2}[A-Z]\\d{22}|CY\\d{2}[A-Z]\\d{23}|NL\\d{2}[A-Z]{4}\\d{10}|LV\\d{2}[A-Z]{4}" +
            "\\d{13}|(?:BG|BH|GB|IE)\\d{2}[A-Z]{4}\\d{14}|GI\\d{2}[A-Z]{4}\\d{15}|RO\\d{2}[A-Z]{4}\\d{16}|KW" +
            "\\d{2}[A-Z]{4}\\d{22}|MT\\d{2}[A-Z]{4}\\d{23}|NO\\d{13}|(?:DK|FI|GL|FO)\\d{16}|MK\\d{17}" +
            "|(?:AT|EE|KZ|LU|XK)\\d{18}|(?:BA|HR|LI|CH|CR)\\d{19}|(?:GE|DE|LT|ME|RS)\\d{20}|IL\\d{21}|" +
            "(?:AD|CZ|ES|MD|SA)\\d{22}|PT\\d{23}|(?:BE|IS)\\d{24}|(?:FR|MR|MC)\\d{25}|(?:AL|DO|LB|PL)\\d{26}|" +
            "(?:AZ|HU)\\d{27}|(?:GR|MU)\\d{28})$", message = "Error! Enter the correct IBAN.")
    private String iban;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "([a-zA-Z]{4})([a-zA-Z]{2})" +
            "(([2-9a-zA-Z]{1})([0-9a-np-zA-NP-Z]{1}))" +
            "((([0-9a-wy-zA-WY-Z]{1})" +
            "([0-9a-zA-Z]{2}))|([xX]{3})|)", message = "Error! Enter the correct BIC code.")
    private String bic;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Max(value = 3, message = "Error! Role id must be not greater than 3.")
    private Long role_id;
}
