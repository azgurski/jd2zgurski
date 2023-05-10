package com.azgurski.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Setter
@Getter
@Builder
@EqualsAndHashCode(exclude = {
        "slots"
})
@ToString(exclude = {
        "slots"
})
@Entity
@Table(name = "h_restaurants")
public class HibernateRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column
    private String name;

    @Column
    private String email;

    //@NotEmpty(message = "Email should be not empty.")
    //@Email(message = "Email should be valid.")
    //private String email;

    @Column
    private String phone;

    @Column(name = "street_house")
    private String streetHouse;

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

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "role_id")
    private Long roleId;

    @Column
    private String website;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "emailUserAuth", column = @Column(name = "email_user_auth")),
            @AttributeOverride(name = "passwordUserAuth", column = @Column(name = "password_user_auth"))
    })
    private AuthenticationInfo authenticationInfo;

    @Column
    @Enumerated(EnumType.STRING)
    private Capacity capacity = Capacity.NOT_SELECTED;

    @OneToMany(mappedBy = "h_restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateSlot> slots = Collections.emptySet();

//    @OneToMany(mappedBy = "h_restaurant") // restaurant - поле из класса Billing data
//    @JsonManagedReference
//    private List<BillingData> billingData = Collections.emptyList();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}