package com.zgurski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
//@EqualsAndHashCode(exclude = {
//        "hibernateRestaurant"
//})
@ToString(exclude = {
        "hibernateRestaurants"
})
@Entity
@Cacheable("h_roles")
@Table(name = "h_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @JsonIgnore
    private Long roleId;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private SystemRoles systemRole = SystemRoles.ROLE_USER;

    @Column
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Timestamp created;

    @Column
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Timestamp changed;

    @ManyToMany
    @JoinTable(name = "l_roles_restaurants",
                joinColumns = @JoinColumn(name = "role_id"),
                inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    @JsonIgnoreProperties("h_roles")
    private Set<HibernateRestaurant> hibernateRestaurants;

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//    }
}