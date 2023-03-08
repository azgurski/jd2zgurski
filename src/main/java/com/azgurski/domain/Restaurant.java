package com.azgurski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restaurant {

    private Long restaurant_id;
    private String name;
    private String email;
    private String phone;
    private String street_house;
    private String city;
    private String postcode;
    private String country;
    private Float longitude;
    private Float latitude;
    private String image_URL;
    private String website;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}