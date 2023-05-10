package com.azgurski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

//JDBCTemplate, REST
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "billing_data")
public class BillingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billing_data_id;


//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
    @Column
    private Long restaurant_id;

    @Column
    @Pattern(regexp = "^(?:(?:IT|SM)\\d{2}[A-Z]\\d{22}|CY\\d{2}[A-Z]\\d{23}|NL\\d{2}[A-Z]{4}\\d{10}|LV\\d{2}[A-Z]{4}" +
            "\\d{13}|(?:BG|BH|GB|IE)\\d{2}[A-Z]{4}\\d{14}|GI\\d{2}[A-Z]{4}\\d{15}|RO\\d{2}[A-Z]{4}\\d{16}|KW" +
            "\\d{2}[A-Z]{4}\\d{22}|MT\\d{2}[A-Z]{4}\\d{23}|NO\\d{13}|(?:DK|FI|GL|FO)\\d{16}|MK\\d{17}" +
            "|(?:AT|EE|KZ|LU|XK)\\d{18}|(?:BA|HR|LI|CH|CR)\\d{19}|(?:GE|DE|LT|ME|RS)\\d{20}|IL\\d{21}|" +
            "(?:AD|CZ|ES|MD|SA)\\d{22}|PT\\d{23}|(?:BE|IS)\\d{24}|(?:FR|MR|MC)\\d{25}|(?:AL|DO|LB|PL)\\d{26}|" +
            "(?:AZ|HU)\\d{27}|(?:GR|MU)\\d{28})$", message = "Enter the correct IBAN.")
    private String iban;

    @Column
    @Pattern(regexp = "([a-zA-Z]{4})([a-zA-Z]{2})" +
            "(([2-9a-zA-Z]{1})([0-9a-np-zA-NP-Z]{1}))" +
            "((([0-9a-wy-zA-WY-Z]{1})" +
            "([0-9a-zA-Z]{2}))|([xX]{3})|)", message = "Enter the correct BIC code.")
    private String bic;

    @Column
    @Max(value = 3, message = "Role id must be not greater than 3")
    private Long role_id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
