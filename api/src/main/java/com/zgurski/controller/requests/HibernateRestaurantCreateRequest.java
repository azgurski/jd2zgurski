package com.zgurski.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class HibernateRestaurantCreateRequest {

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 symbols.")
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