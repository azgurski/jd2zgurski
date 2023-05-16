package com.zgurski.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.cache.annotation.Cacheable;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Setter
@Getter
@Builder
@EqualsAndHashCode(exclude = {
        "slots", "h_roles"
})
@ToString(exclude = {
        "slots", "h_roles"
})
@Entity
@Cacheable("h_restaurants")
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

    @Column
    private String website;

    @Column
    @Enumerated(EnumType.STRING)
    private Capacity capacity = Capacity.NOT_SELECTED;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp created;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //TODO LocalDateTime cодержит у себя под капотом timeStamp
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "emailUserAuth", column = @Column(name = "email_user_auth")),
            @AttributeOverride(name = "passwordUserAuth", column = @Column(name = "password_user_auth"))
    })
    private AuthenticationInfo authenticationInfo;

    @OneToMany(mappedBy = "h_restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    @JsonIgnoreProperties("h_restaurant")
    private Set<HibernateSlot> slots = Collections.emptySet();

    @ManyToMany(mappedBy = "hibernateRestaurants", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("hibernateRestaurants")
//    @JsonIgnore
    private Set<Role> roles = Collections.emptySet();

//
//    @OneToMany(mappedBy = "h_restaurant") // restaurant - поле из класса Billing data
//    @JsonManagedReference
//    private List<BillingData> billingData = Collections.emptyList();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}