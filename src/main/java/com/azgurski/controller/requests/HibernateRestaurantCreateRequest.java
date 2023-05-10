package com.azgurski.controller.requests;

import com.azgurski.domain.Capacity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
//@Validated
public class HibernateRestaurantCreateRequest {

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

    private String role_id;

    private String website;

    private String email_user_auth;

    private String password_user_auth;

//    private String capacity;


}

//name          varchar(50)                                             not null,
//--     email         varchar(100)                                            not null,
//--     phone         varchar(50)                                             not null,
//--     street_house  varchar(50)                                             not null,
//--     city          varchar(20)                                             not null,
//--     postcode      varchar(10)                                             not null,
//--     country       varchar(20)                                             not null,
//--     longitude     real,
//--     latitude      real,
//--     "image_URL"   varchar(500),
//--     created       timestamp(6)                                            not null,
//--     changed       timestamp(6)                                            not null,
//--     is_deleted    boolean default false                                   not null,
//--     role_id       bigint  default 1                                       not null
//--         constraint restaurants_c_roles_role_id_fk
//--             references public.c_roles,
//--     website       varchar(100)