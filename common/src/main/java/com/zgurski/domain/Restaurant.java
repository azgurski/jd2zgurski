package com.zgurski.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurant_id;

    @Column
    private String name;

    @Column
    private String email;

    //@NotEmpty(message = "Email should be not empty.")
    //@Email(message = "Email should be valid.")
    //private String email;

    @Column
    private String phone;

    @Column
    private String street_house;

    @Column
    private String city;

    @Column
    private String postcode;

    @Column
    private String country;

    @Column
    private Float longitude;

    @Column
    private Float latitude;

    @Column
    private String image_URL;

    @Column
    private String role_id;

    @Column
    private String website;

//    @OneToMany(mappedBy = "restaurant") // restaurant - поле из класса Billing data
//    @JsonManagedReference
//    private List<BillingData> billingData = Collections.emptyList();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}